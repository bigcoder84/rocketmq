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

package org.apache.rocketmq.common.subscription;

import org.apache.rocketmq.common.MixAll;

public class SubscriptionGroupConfig {

    /**
     * 消费组名
     */
    private String groupName;
    /**
     * 是否可以消费，默认为true，如果consumeEnable=false，该消费组无法拉取消息，因而无法消费消息
     */
    private boolean consumeEnable = true;
    /**
     * 是否允许从队列最小偏移量开始消费，默认为true，目前未使用该参数
     */
    private boolean consumeFromMinEnable = true;
    /**
     * 设置该消费组是否能以广播模式消费，默认为true，如果设置为false，表示只能以集群模式消费
     */
    private boolean consumeBroadcastEnable = true;
    /**
     * 重试队列个数，默认为1，每一个Broker上有一个重试队列
     */
    private int retryQueueNums = 1;
    /**
     * 消息最大重试次数，默认16次
     */
    private int retryMaxTimes = 16;
    /**
     * 主节点ID
     */
    private long brokerId = MixAll.MASTER_ID;
    /**
     * 如果消息堵塞（主节点），将转向该brokerId的服务器上拉取消息，默认为1
     */
    private long whichBrokerWhenConsumeSlowly = 1;
    /**
     * 当消费发生变化时，是否
     * 立即进行消息队列重新负载。消费组订阅信息配置信息存储在Broker
     * 的 ${ROCKET_HOME}/store/config/subscriptionGroup.json中。
     * BrokerConfig.autoCreateSubscriptionGroup默认为true，表示在第
     * 一次使用消费组配置信息时如果不存在消费组，则使用上述默认值自
     * 动创建一个，如果为false，则只能通过客户端命令mqadmin
     * updateSubGroup创建消费组后再修改相关参数
     */
    private boolean notifyConsumerIdsChangedEnable = true;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isConsumeEnable() {
        return consumeEnable;
    }

    public void setConsumeEnable(boolean consumeEnable) {
        this.consumeEnable = consumeEnable;
    }

    public boolean isConsumeFromMinEnable() {
        return consumeFromMinEnable;
    }

    public void setConsumeFromMinEnable(boolean consumeFromMinEnable) {
        this.consumeFromMinEnable = consumeFromMinEnable;
    }

    public boolean isConsumeBroadcastEnable() {
        return consumeBroadcastEnable;
    }

    public void setConsumeBroadcastEnable(boolean consumeBroadcastEnable) {
        this.consumeBroadcastEnable = consumeBroadcastEnable;
    }

    public int getRetryQueueNums() {
        return retryQueueNums;
    }

    public void setRetryQueueNums(int retryQueueNums) {
        this.retryQueueNums = retryQueueNums;
    }

    public int getRetryMaxTimes() {
        return retryMaxTimes;
    }

    public void setRetryMaxTimes(int retryMaxTimes) {
        this.retryMaxTimes = retryMaxTimes;
    }

    public long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(long brokerId) {
        this.brokerId = brokerId;
    }

    public long getWhichBrokerWhenConsumeSlowly() {
        return whichBrokerWhenConsumeSlowly;
    }

    public void setWhichBrokerWhenConsumeSlowly(long whichBrokerWhenConsumeSlowly) {
        this.whichBrokerWhenConsumeSlowly = whichBrokerWhenConsumeSlowly;
    }

    public boolean isNotifyConsumerIdsChangedEnable() {
        return notifyConsumerIdsChangedEnable;
    }

    public void setNotifyConsumerIdsChangedEnable(final boolean notifyConsumerIdsChangedEnable) {
        this.notifyConsumerIdsChangedEnable = notifyConsumerIdsChangedEnable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (brokerId ^ (brokerId >>> 32));
        result = prime * result + (consumeBroadcastEnable ? 1231 : 1237);
        result = prime * result + (consumeEnable ? 1231 : 1237);
        result = prime * result + (consumeFromMinEnable ? 1231 : 1237);
        result = prime * result + (notifyConsumerIdsChangedEnable ? 1231 : 1237);
        result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
        result = prime * result + retryMaxTimes;
        result = prime * result + retryQueueNums;
        result =
            prime * result + (int) (whichBrokerWhenConsumeSlowly ^ (whichBrokerWhenConsumeSlowly >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubscriptionGroupConfig other = (SubscriptionGroupConfig) obj;
        if (brokerId != other.brokerId)
            return false;
        if (consumeBroadcastEnable != other.consumeBroadcastEnable)
            return false;
        if (consumeEnable != other.consumeEnable)
            return false;
        if (consumeFromMinEnable != other.consumeFromMinEnable)
            return false;
        if (groupName == null) {
            if (other.groupName != null)
                return false;
        } else if (!groupName.equals(other.groupName))
            return false;
        if (retryMaxTimes != other.retryMaxTimes)
            return false;
        if (retryQueueNums != other.retryQueueNums)
            return false;
        if (whichBrokerWhenConsumeSlowly != other.whichBrokerWhenConsumeSlowly)
            return false;
        if (notifyConsumerIdsChangedEnable != other.notifyConsumerIdsChangedEnable)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SubscriptionGroupConfig [groupName=" + groupName + ", consumeEnable=" + consumeEnable
            + ", consumeFromMinEnable=" + consumeFromMinEnable + ", consumeBroadcastEnable="
            + consumeBroadcastEnable + ", retryQueueNums=" + retryQueueNums + ", retryMaxTimes="
            + retryMaxTimes + ", brokerId=" + brokerId + ", whichBrokerWhenConsumeSlowly="
            + whichBrokerWhenConsumeSlowly + ", notifyConsumerIdsChangedEnable="
            + notifyConsumerIdsChangedEnable + "]";
    }
}
