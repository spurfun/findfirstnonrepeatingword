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
>>>>>...  
  
 __2.map__  
>依次读取12个block文件，找出每个block中不重复的word，遍历完一个block后，将不重复的word哈希到12个hashfile中，记录格式如step1,确保相同的word进入>一个hashfile  

__3.reduce__
>依次读取step2中生成的12个hashfile，找出每个hashfile中第一个不重复的word，缓存word及其position，遍历完hashfile后，找到缓存中postion最小的word，即为第一个不重复的词  
