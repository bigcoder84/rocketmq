/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.store.config;

import java.io.File;
import org.apache.rocketmq.common.annotation.ImportantField;
import org.apache.rocketmq.store.ConsumeQueue;

public class MessageStoreConfig {
    //The root directory in which the log data is kept
    @ImportantField
    private String storePathRootDir = System.getProperty("user.home") + File.separator + "store";

    //The directory in which the commitlog is kept
    @ImportantField
    private String storePathCommitLog = System.getProperty("user.home") + File.separator + "store"
        + File.separator + "commitlog";

    // CommitLog file size,default is 1G
    private int mappedFileSizeCommitLog = 1024 * 1024 * 1024;
    // ConsumeQueue file size,default is 30W
    private int mappedFileSizeConsumeQueue = 300000 * ConsumeQueue.CQ_STORE_UNIT_SIZE;
    // enable consume queue ext
    private boolean enableConsumeQueueExt = false;
    // ConsumeQueue extend file size, 48M
    private int mappedFileSizeConsumeQueueExt = 48 * 1024 * 1024;
    // Bit count of filter bit map.
    // this will be set by pipe of calculate filter bit map.
    private int bitMapLengthConsumeQueueExt = 64;

    // CommitLog flush interval
    // flush data to disk
    /**
     * ConmmitLog 刷盘线程任务运行间隔时间
     */
    @ImportantField
    private int flushIntervalCommitLog = 500;

    // Only used if TransientStorePool enabled
    // flush data to FileChannel
    @ImportantField
    private int commitIntervalCommitLog = 200;

    /**
     * introduced since 4.0.x. Determine whether to use mutex reentrantLock when putting message.<br/>
     * By default it is set to false indicating using spin lock when putting message.
     */
    private boolean useReentrantLockWhenPutMessage = false;

    // Whether schedule flush,default is real-time
    @ImportantField
    private boolean flushCommitLogTimed = false;
    // ConsumeQueue flush interval
    private int flushIntervalConsumeQueue = 1000;
    // Resource reclaim interval
    private int cleanResourceInterval = 10000;
    // CommitLog removal interval
    private int deleteCommitLogFilesInterval = 100;
    // ConsumeQueue removal interval
    /**
     * 删除物理文件的间隔时间，在一次清除过程中，可能需要被删除的文件不止一个，该值指定两次删除文件的间隔时间
     */
    private int deleteConsumeQueueFilesInterval = 100;
    /**
     * 在清除过期文件时，如果该文件被其他线程占用（引用次数大于0，比如读取消息），此时会
     * 阻止此次删除任务，同时在第一次试图删除该文件时记录当前时间戳，destroyMapedFileIntervalForcibly表示第一次拒绝删除之后能
     * 保留文件的最大时间，在此时间内，同样可以被拒绝删除，超过该时间后，会将引用次数设置为负数，文件将被强制删除
     */
    private int destroyMapedFileIntervalForcibly = 1000 * 120;
    private int redeleteHangedFileInterval = 1000 * 120;
    // When to delete,default is at 4 am
    @ImportantField
    private String deleteWhen = "04";
    /**
     * 表示CommitLog文件、ConsumeQueue文件所在磁盘分区的最大使用量，如果超过该值，则需要立即清除过
     * 期文件，默认 75，如果超过了 75% 就会触发清理过期文件的动作
     */
    private int diskMaxUsedSpaceRatio = 75;
    // The number of hours to keep a log file before deleting it (in hours)
    /**
     * 日志文件保存的小时数
     */
    @ImportantField
    private int fileReservedTime = 72;
    // Flow control for ConsumeQueue
    private int putMsgIndexHightWater = 600000;
    // The maximum size of message,default is 4M
    private int maxMessageSize = 1024 * 1024 * 4;
    // Whether check the CRC32 of the records consumed.
    // This ensures no on-the-wire or on-disk corruption to the messages occurred.
    // This check adds some overhead,so it may be disabled in cases seeking extreme performance.
    private boolean checkCRCOnRecover = true;
    // How many pages are to be flushed when flush CommitLog
    /**
     * 一次刷盘任务至少包含页数，如果待写入数据不足，小于该参数配置的值，将忽略本次刷盘任务，默认4页
     */
    private int flushCommitLogLeastPages = 4;
    // How many pages are to be committed when commit data to file
    private int commitCommitLogLeastPages = 4;
    // Flush page size when the disk in warming state
    private int flushLeastPagesWhenWarmMapedFile = 1024 / 4 * 16;
    // How many pages are to be flushed when flush ConsumeQueue
    private int flushConsumeQueueLeastPages = 2;
    /**
     * 两次真实刷盘任务的最大间隔时间，默认10s
     */
    private int flushCommitLogThoroughInterval = 1000 * 10;
    private int commitCommitLogThoroughInterval = 200;
    private int flushConsumeQueueThoroughInterval = 1000 * 60;
    @ImportantField
    private int maxTransferBytesOnMessageInMemory = 1024 * 256;
    @ImportantField
    private int maxTransferCountOnMessageInMemory = 32;
    @ImportantField
    private int maxTransferBytesOnMessageInDisk = 1024 * 64;
    @ImportantField
    private int maxTransferCountOnMessageInDisk = 8;
    @ImportantField
    private int accessMessageInMemoryMaxRatio = 40;
    /**
     * 是否开启消息索引
     */
    @ImportantField
    private boolean messageIndexEnable = true;
    private int maxHashSlotNum = 5000000;
    private int maxIndexNum = 5000000 * 4;
    private int maxMsgsNumBatch = 64;
    @ImportantField
    private boolean messageIndexSafe = false;
    private int haListenPort = 10912;
    private int haSendHeartbeatInterval = 1000 * 5;
    private int haHousekeepingInterval = 1000 * 20;
    private int haTransferBatchSize = 1024 * 32;
    @ImportantField
    private String haMasterAddress = null;
    private int haSlaveFallbehindMax = 1024 * 1024 * 256;
    @ImportantField
    private BrokerRole brokerRole = BrokerRole.ASYNC_MASTER;
    @ImportantField
    private FlushDiskType flushDiskType = FlushDiskType.ASYNC_FLUSH;
    private int syncFlushTimeout = 1000 * 5;
    private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
    private long flushDelayOffsetInterval = 1000 * 10;
    /**
     * 在一些情况下，即使CommitLog文件已经过期，也可能因为某些原因（例如消费者消费速度慢等）而无法被清理。
     * 此时，如果cleanFileForciblyEnable参数被设置为true，那么RocketMQ将会强制地清理这些文件，从而释放磁盘空间。
     */
    @ImportantField
    private boolean cleanFileForciblyEnable = true;
    private boolean warmMapedFileEnable = false;
    private boolean offsetCheckInSlave = false;
    private boolean debugLockEnable = false;
    private boolean duplicationEnable = false;
    private boolean diskFallRecorded = true;
    private long osPageCacheBusyTimeOutMills = 1000;
    private int defaultQueryMaxNum = 32;

    /**
     * 是否开启 transientStorePool 机制
     *
     * 开启TransientStorePool机制，消息数据首先被写入到堆外内存（也就是TransientStorePool中的DirectByteBuffer）。然后由刷盘线程（CommitRealTimeService）
     * 在合适的时机把数据从堆外内存复制到内存映射区域（MappedByteBuffer），并调用force()方法将数据写入磁盘。
     *
     * 有了 TransientStorePool 的存在，消息可以批量写入内存缓冲区，RocketMQ也就可以有效地控制何时以及如何将脏页（Dirty Page，即已修改但还未写入磁盘的内存页）
     * 刷写到磁盘，避免了操作系统自动进行的随机性、不可预测的脏页刷写操作，从而提升了I/O性能，特别是在大量写入请求的场景下。
     *
     * 值得一提的是，使用TransientStorePool并非没有代价。因为需要额外的一次内存复制操作，即从堆外内存复制到内存映射区域。
     * 但是在大多数情况下，通过控制脏页刷写带来的性能提升，相比于增加的内存复制开销，更加明显。
     *
     * 并且开启transientStorePool机制后，由于消息数据会先写入 堆外内存，然后由特定后台线程（CommitRealTimeService），将堆外内存中的修改
     * commit 到 内存映射区域，而这一步如果发生 断电、服务宕机，都会产生消息丢失。而传统的异步刷盘，由于消息是直接写入内存映射区域，所以服务宕机
     * 并不会丢失数据，只有在服务器突然断电时才会丢失少量数据。
     *
     * 所以整体来看消息存储可靠性： 同步刷盘（任何情况不会丢失消息）> 异步刷盘+transientStorePool=false（突然断电丢失少量消息）> 异步刷盘+transientStorePool=true（突然断电或服务宕机都会丢失消息）
     *
     */
    @ImportantField
    private boolean transientStorePoolEnable = false;
    private int transientStorePoolSize = 5;
    private boolean fastFailIfNoBufferInStorePool = false;

    private boolean enableDLegerCommitLog = false;
    private String dLegerGroup;
    private String dLegerPeers;
    private String dLegerSelfId;

    public boolean isDebugLockEnable() {
        return debugLockEnable;
    }

    public void setDebugLockEnable(final boolean debugLockEnable) {
        this.debugLockEnable = debugLockEnable;
    }

    public boolean isDuplicationEnable() {
        return duplicationEnable;
    }

    public void setDuplicationEnable(final boolean duplicationEnable) {
        this.duplicationEnable = duplicationEnable;
    }

    public long getOsPageCacheBusyTimeOutMills() {
        return osPageCacheBusyTimeOutMills;
    }

    public void setOsPageCacheBusyTimeOutMills(final long osPageCacheBusyTimeOutMills) {
        this.osPageCacheBusyTimeOutMills = osPageCacheBusyTimeOutMills;
    }

    public boolean isDiskFallRecorded() {
        return diskFallRecorded;
    }

    public void setDiskFallRecorded(final boolean diskFallRecorded) {
        this.diskFallRecorded = diskFallRecorded;
    }

    public boolean isWarmMapedFileEnable() {
        return warmMapedFileEnable;
    }

    public void setWarmMapedFileEnable(boolean warmMapedFileEnable) {
        this.warmMapedFileEnable = warmMapedFileEnable;
    }

    public int getMappedFileSizeCommitLog() {
        return mappedFileSizeCommitLog;
    }

    public void setMappedFileSizeCommitLog(int mappedFileSizeCommitLog) {
        this.mappedFileSizeCommitLog = mappedFileSizeCommitLog;
    }

    public int getMappedFileSizeConsumeQueue() {

        int factor = (int) Math.ceil(this.mappedFileSizeConsumeQueue / (ConsumeQueue.CQ_STORE_UNIT_SIZE * 1.0));
        return (int) (factor * ConsumeQueue.CQ_STORE_UNIT_SIZE);
    }

    public void setMappedFileSizeConsumeQueue(int mappedFileSizeConsumeQueue) {
        this.mappedFileSizeConsumeQueue = mappedFileSizeConsumeQueue;
    }

    public boolean isEnableConsumeQueueExt() {
        return enableConsumeQueueExt;
    }

    public void setEnableConsumeQueueExt(boolean enableConsumeQueueExt) {
        this.enableConsumeQueueExt = enableConsumeQueueExt;
    }

    public int getMappedFileSizeConsumeQueueExt() {
        return mappedFileSizeConsumeQueueExt;
    }

    public void setMappedFileSizeConsumeQueueExt(int mappedFileSizeConsumeQueueExt) {
        this.mappedFileSizeConsumeQueueExt = mappedFileSizeConsumeQueueExt;
    }

    public int getBitMapLengthConsumeQueueExt() {
        return bitMapLengthConsumeQueueExt;
    }

    public void setBitMapLengthConsumeQueueExt(int bitMapLengthConsumeQueueExt) {
        this.bitMapLengthConsumeQueueExt = bitMapLengthConsumeQueueExt;
    }

    public int getFlushIntervalCommitLog() {
        return flushIntervalCommitLog;
    }

    public void setFlushIntervalCommitLog(int flushIntervalCommitLog) {
        this.flushIntervalCommitLog = flushIntervalCommitLog;
    }

    public int getFlushIntervalConsumeQueue() {
        return flushIntervalConsumeQueue;
    }

    public void setFlushIntervalConsumeQueue(int flushIntervalConsumeQueue) {
        this.flushIntervalConsumeQueue = flushIntervalConsumeQueue;
    }

    public int getPutMsgIndexHightWater() {
        return putMsgIndexHightWater;
    }

    public void setPutMsgIndexHightWater(int putMsgIndexHightWater) {
        this.putMsgIndexHightWater = putMsgIndexHightWater;
    }

    public int getCleanResourceInterval() {
        return cleanResourceInterval;
    }

    public void setCleanResourceInterval(int cleanResourceInterval) {
        this.cleanResourceInterval = cleanResourceInterval;
    }

    public int getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public boolean isCheckCRCOnRecover() {
        return checkCRCOnRecover;
    }

    public boolean getCheckCRCOnRecover() {
        return checkCRCOnRecover;
    }

    public void setCheckCRCOnRecover(boolean checkCRCOnRecover) {
        this.checkCRCOnRecover = checkCRCOnRecover;
    }

    public String getStorePathCommitLog() {
        return storePathCommitLog;
    }

    public void setStorePathCommitLog(String storePathCommitLog) {
        this.storePathCommitLog = storePathCommitLog;
    }

    public String getDeleteWhen() {
        return deleteWhen;
    }

    public void setDeleteWhen(String deleteWhen) {
        this.deleteWhen = deleteWhen;
    }

    public int getDiskMaxUsedSpaceRatio() {
        if (this.diskMaxUsedSpaceRatio < 10)
            return 10;

        if (this.diskMaxUsedSpaceRatio > 95)
            return 95;

        return diskMaxUsedSpaceRatio;
    }

    public void setDiskMaxUsedSpaceRatio(int diskMaxUsedSpaceRatio) {
        this.diskMaxUsedSpaceRatio = diskMaxUsedSpaceRatio;
    }

    public int getDeleteCommitLogFilesInterval() {
        return deleteCommitLogFilesInterval;
    }

    public void setDeleteCommitLogFilesInterval(int deleteCommitLogFilesInterval) {
        this.deleteCommitLogFilesInterval = deleteCommitLogFilesInterval;
    }

    public int getDeleteConsumeQueueFilesInterval() {
        return deleteConsumeQueueFilesInterval;
    }

    public void setDeleteConsumeQueueFilesInterval(int deleteConsumeQueueFilesInterval) {
        this.deleteConsumeQueueFilesInterval = deleteConsumeQueueFilesInterval;
    }

    public int getMaxTransferBytesOnMessageInMemory() {
        return maxTransferBytesOnMessageInMemory;
    }

    public void setMaxTransferBytesOnMessageInMemory(int maxTransferBytesOnMessageInMemory) {
        this.maxTransferBytesOnMessageInMemory = maxTransferBytesOnMessageInMemory;
    }

    public int getMaxTransferCountOnMessageInMemory() {
        return maxTransferCountOnMessageInMemory;
    }

    public void setMaxTransferCountOnMessageInMemory(int maxTransferCountOnMessageInMemory) {
        this.maxTransferCountOnMessageInMemory = maxTransferCountOnMessageInMemory;
    }

    public int getMaxTransferBytesOnMessageInDisk() {
        return maxTransferBytesOnMessageInDisk;
    }

    public void setMaxTransferBytesOnMessageInDisk(int maxTransferBytesOnMessageInDisk) {
        this.maxTransferBytesOnMessageInDisk = maxTransferBytesOnMessageInDisk;
    }

    public int getMaxTransferCountOnMessageInDisk() {
        return maxTransferCountOnMessageInDisk;
    }

    public void setMaxTransferCountOnMessageInDisk(int maxTransferCountOnMessageInDisk) {
        this.maxTransferCountOnMessageInDisk = maxTransferCountOnMessageInDisk;
    }

    public int getFlushCommitLogLeastPages() {
        return flushCommitLogLeastPages;
    }

    public void setFlushCommitLogLeastPages(int flushCommitLogLeastPages) {
        this.flushCommitLogLeastPages = flushCommitLogLeastPages;
    }

    public int getFlushConsumeQueueLeastPages() {
        return flushConsumeQueueLeastPages;
    }

    public void setFlushConsumeQueueLeastPages(int flushConsumeQueueLeastPages) {
        this.flushConsumeQueueLeastPages = flushConsumeQueueLeastPages;
    }

    public int getFlushCommitLogThoroughInterval() {
        return flushCommitLogThoroughInterval;
    }

    public void setFlushCommitLogThoroughInterval(int flushCommitLogThoroughInterval) {
        this.flushCommitLogThoroughInterval = flushCommitLogThoroughInterval;
    }

    public int getFlushConsumeQueueThoroughInterval() {
        return flushConsumeQueueThoroughInterval;
    }

    public void setFlushConsumeQueueThoroughInterval(int flushConsumeQueueThoroughInterval) {
        this.flushConsumeQueueThoroughInterval = flushConsumeQueueThoroughInterval;
    }

    public int getDestroyMapedFileIntervalForcibly() {
        return destroyMapedFileIntervalForcibly;
    }

    public void setDestroyMapedFileIntervalForcibly(int destroyMapedFileIntervalForcibly) {
        this.destroyMapedFileIntervalForcibly = destroyMapedFileIntervalForcibly;
    }

    public int getFileReservedTime() {
        return fileReservedTime;
    }

    public void setFileReservedTime(int fileReservedTime) {
        this.fileReservedTime = fileReservedTime;
    }

    public int getRedeleteHangedFileInterval() {
        return redeleteHangedFileInterval;
    }

    public void setRedeleteHangedFileInterval(int redeleteHangedFileInterval) {
        this.redeleteHangedFileInterval = redeleteHangedFileInterval;
    }

    public int getAccessMessageInMemoryMaxRatio() {
        return accessMessageInMemoryMaxRatio;
    }

    public void setAccessMessageInMemoryMaxRatio(int accessMessageInMemoryMaxRatio) {
        this.accessMessageInMemoryMaxRatio = accessMessageInMemoryMaxRatio;
    }

    public boolean isMessageIndexEnable() {
        return messageIndexEnable;
    }

    public void setMessageIndexEnable(boolean messageIndexEnable) {
        this.messageIndexEnable = messageIndexEnable;
    }

    public int getMaxHashSlotNum() {
        return maxHashSlotNum;
    }

    public void setMaxHashSlotNum(int maxHashSlotNum) {
        this.maxHashSlotNum = maxHashSlotNum;
    }

    public int getMaxIndexNum() {
        return maxIndexNum;
    }

    public void setMaxIndexNum(int maxIndexNum) {
        this.maxIndexNum = maxIndexNum;
    }

    public int getMaxMsgsNumBatch() {
        return maxMsgsNumBatch;
    }

    public void setMaxMsgsNumBatch(int maxMsgsNumBatch) {
        this.maxMsgsNumBatch = maxMsgsNumBatch;
    }

    public int getHaListenPort() {
        return haListenPort;
    }

    public void setHaListenPort(int haListenPort) {
        this.haListenPort = haListenPort;
    }

    public int getHaSendHeartbeatInterval() {
        return haSendHeartbeatInterval;
    }

    public void setHaSendHeartbeatInterval(int haSendHeartbeatInterval) {
        this.haSendHeartbeatInterval = haSendHeartbeatInterval;
    }

    public int getHaHousekeepingInterval() {
        return haHousekeepingInterval;
    }

    public void setHaHousekeepingInterval(int haHousekeepingInterval) {
        this.haHousekeepingInterval = haHousekeepingInterval;
    }

    public BrokerRole getBrokerRole() {
        return brokerRole;
    }

    public void setBrokerRole(BrokerRole brokerRole) {
        this.brokerRole = brokerRole;
    }

    public void setBrokerRole(String brokerRole) {
        this.brokerRole = BrokerRole.valueOf(brokerRole);
    }

    public int getHaTransferBatchSize() {
        return haTransferBatchSize;
    }

    public void setHaTransferBatchSize(int haTransferBatchSize) {
        this.haTransferBatchSize = haTransferBatchSize;
    }

    public int getHaSlaveFallbehindMax() {
        return haSlaveFallbehindMax;
    }

    public void setHaSlaveFallbehindMax(int haSlaveFallbehindMax) {
        this.haSlaveFallbehindMax = haSlaveFallbehindMax;
    }

    public FlushDiskType getFlushDiskType() {
        return flushDiskType;
    }

    public void setFlushDiskType(FlushDiskType flushDiskType) {
        this.flushDiskType = flushDiskType;
    }

    public void setFlushDiskType(String type) {
        this.flushDiskType = FlushDiskType.valueOf(type);
    }

    public int getSyncFlushTimeout() {
        return syncFlushTimeout;
    }

    public void setSyncFlushTimeout(int syncFlushTimeout) {
        this.syncFlushTimeout = syncFlushTimeout;
    }

    public String getHaMasterAddress() {
        return haMasterAddress;
    }

    public void setHaMasterAddress(String haMasterAddress) {
        this.haMasterAddress = haMasterAddress;
    }

    public String getMessageDelayLevel() {
        return messageDelayLevel;
    }

    public void setMessageDelayLevel(String messageDelayLevel) {
        this.messageDelayLevel = messageDelayLevel;
    }

    public long getFlushDelayOffsetInterval() {
        return flushDelayOffsetInterval;
    }

    public void setFlushDelayOffsetInterval(long flushDelayOffsetInterval) {
        this.flushDelayOffsetInterval = flushDelayOffsetInterval;
    }

    public boolean isCleanFileForciblyEnable() {
        return cleanFileForciblyEnable;
    }

    public void setCleanFileForciblyEnable(boolean cleanFileForciblyEnable) {
        this.cleanFileForciblyEnable = cleanFileForciblyEnable;
    }

    public boolean isMessageIndexSafe() {
        return messageIndexSafe;
    }

    public void setMessageIndexSafe(boolean messageIndexSafe) {
        this.messageIndexSafe = messageIndexSafe;
    }

    public boolean isFlushCommitLogTimed() {
        return flushCommitLogTimed;
    }

    public void setFlushCommitLogTimed(boolean flushCommitLogTimed) {
        this.flushCommitLogTimed = flushCommitLogTimed;
    }

    public String getStorePathRootDir() {
        return storePathRootDir;
    }

    public void setStorePathRootDir(String storePathRootDir) {
        this.storePathRootDir = storePathRootDir;
    }

    public int getFlushLeastPagesWhenWarmMapedFile() {
        return flushLeastPagesWhenWarmMapedFile;
    }

    public void setFlushLeastPagesWhenWarmMapedFile(int flushLeastPagesWhenWarmMapedFile) {
        this.flushLeastPagesWhenWarmMapedFile = flushLeastPagesWhenWarmMapedFile;
    }

    public boolean isOffsetCheckInSlave() {
        return offsetCheckInSlave;
    }

    public void setOffsetCheckInSlave(boolean offsetCheckInSlave) {
        this.offsetCheckInSlave = offsetCheckInSlave;
    }

    public int getDefaultQueryMaxNum() {
        return defaultQueryMaxNum;
    }

    public void setDefaultQueryMaxNum(int defaultQueryMaxNum) {
        this.defaultQueryMaxNum = defaultQueryMaxNum;
    }

    /**
     * Enable transient commitLog store pool only if transientStorePoolEnable is true and the FlushDiskType is
     * ASYNC_FLUSH
     *
     * @return <tt>true</tt> or <tt>false</tt>
     */
    public boolean isTransientStorePoolEnable() {
        return transientStorePoolEnable && FlushDiskType.ASYNC_FLUSH == getFlushDiskType()
            && BrokerRole.SLAVE != getBrokerRole();
    }

    public void setTransientStorePoolEnable(final boolean transientStorePoolEnable) {
        this.transientStorePoolEnable = transientStorePoolEnable;
    }

    public int getTransientStorePoolSize() {
        return transientStorePoolSize;
    }

    public void setTransientStorePoolSize(final int transientStorePoolSize) {
        this.transientStorePoolSize = transientStorePoolSize;
    }

    public int getCommitIntervalCommitLog() {
        return commitIntervalCommitLog;
    }

    public void setCommitIntervalCommitLog(final int commitIntervalCommitLog) {
        this.commitIntervalCommitLog = commitIntervalCommitLog;
    }

    public boolean isFastFailIfNoBufferInStorePool() {
        return fastFailIfNoBufferInStorePool;
    }

    public void setFastFailIfNoBufferInStorePool(final boolean fastFailIfNoBufferInStorePool) {
        this.fastFailIfNoBufferInStorePool = fastFailIfNoBufferInStorePool;
    }

    public boolean isUseReentrantLockWhenPutMessage() {
        return useReentrantLockWhenPutMessage;
    }

    public void setUseReentrantLockWhenPutMessage(final boolean useReentrantLockWhenPutMessage) {
        this.useReentrantLockWhenPutMessage = useReentrantLockWhenPutMessage;
    }

    public int getCommitCommitLogLeastPages() {
        return commitCommitLogLeastPages;
    }

    public void setCommitCommitLogLeastPages(final int commitCommitLogLeastPages) {
        this.commitCommitLogLeastPages = commitCommitLogLeastPages;
    }

    public int getCommitCommitLogThoroughInterval() {
        return commitCommitLogThoroughInterval;
    }

    public void setCommitCommitLogThoroughInterval(final int commitCommitLogThoroughInterval) {
        this.commitCommitLogThoroughInterval = commitCommitLogThoroughInterval;
    }

    public String getdLegerGroup() {
        return dLegerGroup;
    }

    public void setdLegerGroup(String dLegerGroup) {
        this.dLegerGroup = dLegerGroup;
    }

    public String getdLegerPeers() {
        return dLegerPeers;
    }

    public void setdLegerPeers(String dLegerPeers) {
        this.dLegerPeers = dLegerPeers;
    }

    public String getdLegerSelfId() {
        return dLegerSelfId;
    }

    public void setdLegerSelfId(String dLegerSelfId) {
        this.dLegerSelfId = dLegerSelfId;
    }

    public boolean isEnableDLegerCommitLog() {
        return enableDLegerCommitLog;
    }

    public void setEnableDLegerCommitLog(boolean enableDLegerCommitLog) {
        this.enableDLegerCommitLog = enableDLegerCommitLog;
    }
}
