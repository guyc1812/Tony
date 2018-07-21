# Java Garbage Collection

![image](https://guyuchen.oss-cn-shanghai.aliyuncs.com/deadpool/images/gc.png)

### CG 的主要工作

* 找到不再使用的对象
* 回收它们使用的内存
* 对堆内存布局进行压缩整理

### 关于 GC

1. 对象分配
	
	* 分配原则：
	
		初始对象被分配在Eden区<br>
		对象过大则会直接分配在Old Generation，使用`-XX:PretenureSizeThreshold`来控制
		
	* 分配策略：
	
		bump-the-pointer：核心就是跟踪最后创建的一个对象，在对象创建时，只需要检查最后一个对象后面是否有足够的内存即可，从而大大加快内存分配速度。<br>
		TLAB（Thread-Local Allocation Buffers：是对于多线程而言的，将Eden区分为若干段，每个线程使用独立的一段，避免相互影响。TLAB结合bump-the-pointer技术，将保证每个线程都使用Eden区的一段，并快速的分配内存

1. Minor GC(“停止-复制”算法)

	* 当Eden区满的时候，执行Minor GC，未被清理的对象移动到 Survivor0
	* 再次Eden区满的时候，执行Minor GC，未被清理的对象移动到 Survivor1<br>
	同时清空Survivor0，清理消亡对象，存活对象晋级到Old Generation，或复制到Survivor1
	* 存活对象晋级到Old Generation的条件：
		15次Minor GC后仍然存活到对象(-XX:MaxTenuringThreshold，默认15次)<br>
		Survivor+Eden中存活下来的内存超过了10%，则需要一部分对象晋级
    * Eden：Survivor0：Survivor1 = 8:1:1<br>
    `-XX:SurvivorRatio`调控Eden和Survivor的比例，默认是8

1. Major GC(Full GC)

	当Old Generation区满的时候，进行Major GC，此时会产生时空停顿(stop-the-world)。

1. GC

	* Serial GC
	
		`-XX:+UseSerialGC`<br>
		单线程，可进行内存压缩整理<br>
		适合win32或单核jvm
		
	* Throughput GC (Parallel GC)
	
		`-XX:+UseParallelGC, -XX:+UseParallelOldGC(JDK1.7)`<br>
		设计目的：拓展Serial GC性能<br>
		算法概述：采用多线程，但依旧会在GC时暂停所有应用线程，产生“停顿”<br>
		server级默认收集器
		
	* CMS GC
	
		`-XX:+UseConcMarkSweepGC, -XX:+UseParNewGC`<br>
		设计目的：消除停顿<br>
		算法概述：不使用Throughput算法，使用ParNew算法来进行GC，使用后台多线程来减少Full GC的频次。<br>
		区别:
		1. 是需要足够的CPU资源，通过后台线程进行GC来消除长时间停顿
		1. 不再进行内存压缩整理，当堆碎片化无法继续分配对象时，会退回Serial GC，进行单线程GC
			
	* G1 GC
	
		`-XX:UseG1GC`<br>
		设计目的：为了尽量缩短处理超大堆(>4G)时产生的停顿。<br>
		算法概述：将堆划分不同区域，采用复制活跃对象的方式，同时进行GC和内存压缩整理，比CMS更不易遇到Full GC
	
###### **References**

* JAVA性能权威指南