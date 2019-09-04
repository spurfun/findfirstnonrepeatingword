**题目：**  
  >有一个 100GB 的文件，里面内容是文本，要求：  
  >找出第一个不重复的词  
  >只允许扫一遍原文件  
  >尽量少的 IO  
  >内存限制 16G  
  
**方法：**   
  __1.split__  
>将文件均分成12个block，为确保均分，逐行读取原文件，每100个word写入block0，下100个word写入block2，直至block11，再写入block0  
>因需找出第一个不重复单词，所以需记录word的位置，word写入block的方式为block每行写入word position，position指word是文件中的第几个词，如下：  
>>>word1 position1   
>>>word2 position2   
    ...  
  
 __2.map__  
>依次读取12个block文件，找出每个block中不重复的word，遍历完一个block后，将不重复的word哈希到12个hashfile中，记录格式如step1,以此确保相同的word进入一个hashfile  

__3.reduce__
>依次读取step2中生成的12个hashfile，找出每个hashfile中第一个不重复的word，缓存word及其position，遍历完hashfile后，找到缓存中postion最小的word，即为第一个不重复的词  


**设计逻辑:**  
三个阶段的划分参考Hadoop中MapReduce的计算方式  
在MapReduce中使用多个节点来并行处理任务，一个完整的MapReduce任务除了map和reduce两个计算阶段，还有map之前对文件的切分split，map和reduce任务之间的数据传递shuffle阶段，及最终的整理finalize阶段  
如统计一个文本中所有单词出现的次数wordCount，使用mapReduce的计算过程大致如下：  
![wordCount](https://github.com/spurfun/findfirstnonrepeatingword/blob/master/src/main/resources/wordCount.png)  
在本题中这里我们假设有m个节点，使用MapReduce大致执行流程如下：  
>split：  
>将文件均分到m个节点上，将这些划分之后的文件我们称为block，因为题目要求找到第一个不重复的词，这里我们将词及每个词在原文件中的位置（即第几个词）发送到m个节点上  
>map：  
>对于m个节点中的任意一个节点，找出各自block中不重复的词并记录每个词的位置  
>shuffle：  
>有n个节点，这n个节点做reduce计算用，将map的计算结果发送到这n个节点上，其中相同的词及位置发送到同一节点  
>reduce：  
>对于n个节点中的任意一个节点，找到节点上所有词中第一个不重复的词  
>finalize：  
>将reduce阶段的n个词发送到一个节点上，找出其中位置值最小的词，即为题目要求的第一个不重复的词

将上述流程用单节点实现，每个阶段的计算结果落到磁盘，对文件foreach遍历代替节点的并行执行  
在上述shuffle阶段，代码中与map阶段合并  
在上述finalize阶段，代码中与reduce阶段合并  


**获取每个文件中不重复的词及第一个不重复词的算法：**  
代码见https://github.com/spurfun/findfirstnonrepeatingword/blob/master/src/main/java/com/pingCAP/findFirstNonRepeatingWord/filetool/FindNonRepeationgWord.java  
维持一个hashmap和双向链表doubleList  
doubleList的node存储词及其位置，记为word  
hashmap的key为词，value为双向链表的节点  
按行遍历文件，如果词string不在hashmap的key中则执行：  
*if not hashmap.contains(string){  
hashmap.put(string, word); //string加入map  
doubleList.addLast(word);  //string放入双向链表尾部  
}*  
如果词string在hashmap的key中，代表词string重复出现，则执行  
*if hashmap.contains(string){  
hashmap.put(string, null); //将key中对应的key设为空值  
doubleList.remove(hashmap.get(string));  //在doubleList中删除map对应的value  
}*  
遍历完成后，doubleList中的值中的词即为不重复的词  
doubleList的头节点中的词即为第一个不重复的词  
时间复杂度为O(n)，n为文件中词数  


**IO成本**  
整个处理流程中需要文件落盘两次，读取文件3次，io成本:O(3n)  
**时间复杂度**  
文件split遍历一次，map阶段遍历一次，reduce阶段遍历一次，时间复杂度O(3n)  
n为原文件中单词个数  


**优化方案**  
在split阶段每次读固定个单词数，如每次读10000个词，对这些词进行代码中的map操作：找出1w个词中不重复的词，并hash到不同的文件，之后的reduce操作不变    
与之间相比，会减少一次文件io和一次遍历文件的过程  
