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
package org.apache.rocketmq.client.impl.consumer;

import java.util.List;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.body.ConsumeMessageDirectlyResult;

/**
 * RocketMQ使用ConsumeMessageService来实现消息消费的处理逻辑。
 *
 * PullMessageService负责对消
 * 息队列进行消息拉取，从远端服务器拉取消息后存入ProcessQueue消
 * 息处理队列中，然后调用
 * ConsumeMessageService#submitConsumeRequest方法进行消息消费。
 * 使用线程池消费消息，确保了消息拉取与消息消费的解耦。
 *
 */
public interface ConsumeMessageService {
    void start();

    void shutdown();

    void updateCorePoolSize(int corePoolSize);

    void incCorePoolSize();

    void decCorePoolSize();

    int getCorePoolSize();

    /**
     * 直接消费消息，主要用于通过管理命令接收消费消息
     * @param msg 消息
     * @param brokerName broker名称
     * @return
     */
    ConsumeMessageDirectlyResult consumeMessageDirectly(final MessageExt msg, final String brokerName);

    /**
     * 提交消息消费
     * @param msgs 消息列表，默认一次从服务器拉取32条消息
     * @param processQueue 消息处理队列
     * @param messageQueue 消息所属消费队列
     * @param dispathToConsume
     */
    void submitConsumeRequest(
        final List<MessageExt> msgs,
        final ProcessQueue processQueue,
        final MessageQueue messageQueue,
        final boolean dispathToConsume);
}
