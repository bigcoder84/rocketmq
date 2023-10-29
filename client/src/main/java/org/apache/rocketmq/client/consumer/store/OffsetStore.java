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
package org.apache.rocketmq.client.consumer.store;

import java.util.Map;
import java.util.Set;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * Offset store interface
 */
public interface OffsetStore {
    /**
     * 从消息进度存储文件加载消息进度到内存
     * Load
     */
    void load() throws MQClientException;

    /**
     * 更新内存中的消息消费进度
     * @param mq 消息消费队列
     * @param offset 消息消费偏移量
     * @param increaseOnly true表示offset必须大于内存中当前的消费偏移量才更新
     */
    void updateOffset(final MessageQueue mq, final long offset, final boolean increaseOnly);

    /**
     * 读取消息消费进度
     * @param mq 消息消费队列
     * @param type 读取方式，可选值包括
     * READ_FROM_MEMORY，即从内存中读取，READ_FROM_STORE，即从磁盘中读取，MEMORY_FIRST_THEN_STORE，即先从内存中读取，再从磁盘中读取
     * @return
     */
    long readOffset(final MessageQueue mq, final ReadOffsetType type);

    /**
     * 持久化指定消息队列进度到磁盘
     * @param mqs
     */
    void persistAll(final Set<MessageQueue> mqs);

    /**
     * Persist the offset,may be in local storage or remote name server
     */
    void persist(final MessageQueue mq);

    /**
     * 将消息队列的消息消费进度从内存中移除。
     * @param mq
     */
    void removeOffset(MessageQueue mq);

    /**
     * 复制该主题下所有消息队列的消息消费进度。
     * @param topic
     * @return
     */
    Map<MessageQueue, Long> cloneOffsetTable(String topic);

    /**
     * 使用集群模式更新存储在Broker端的消息消费进度
     * @param mq
     * @param offset
     * @param isOneway
     */
    void updateConsumeOffsetToBroker(MessageQueue mq, long offset, boolean isOneway) throws RemotingException,
        MQBrokerException, InterruptedException, MQClientException;
}
