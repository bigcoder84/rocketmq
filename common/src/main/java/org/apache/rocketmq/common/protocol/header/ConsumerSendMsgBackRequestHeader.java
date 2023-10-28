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

package org.apache.rocketmq.common.protocol.header;

import org.apache.rocketmq.remoting.CommandCustomHeader;
import org.apache.rocketmq.remoting.annotation.CFNotNull;
import org.apache.rocketmq.remoting.annotation.CFNullable;
import org.apache.rocketmq.remoting.exception.RemotingCommandException;

public class ConsumerSendMsgBackRequestHeader implements CommandCustomHeader {
    /**
     * 消息物理偏移量
     */
    @CFNotNull
    private Long offset;
    @CFNotNull
    private String group;
    /**
     * 延迟级别。RcketMQ不支持精确的定时消息调度，而是提供几个延时级别，MessageStoreConfig#messageDelayLevel = "1s 5s 10s 30s 1m 2m
     * 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"，delayLevel=1，表示延迟5s，delayLevel=2，表示延迟10s。
     */
    @CFNotNull
    private Integer delayLevel;
    /**
     * 消息ID
     */
    private String originMsgId;
    /**
     * 消息topic
     */
    private String originTopic;
    @CFNullable
    private boolean unitMode = false;
    private Integer maxReconsumeTimes;

    @Override
    public void checkFields() throws RemotingCommandException {

    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getDelayLevel() {
        return delayLevel;
    }

    public void setDelayLevel(Integer delayLevel) {
        this.delayLevel = delayLevel;
    }

    public String getOriginMsgId() {
        return originMsgId;
    }

    public void setOriginMsgId(String originMsgId) {
        this.originMsgId = originMsgId;
    }

    public String getOriginTopic() {
        return originTopic;
    }

    public void setOriginTopic(String originTopic) {
        this.originTopic = originTopic;
    }

    public boolean isUnitMode() {
        return unitMode;
    }

    public void setUnitMode(boolean unitMode) {
        this.unitMode = unitMode;
    }

    public Integer getMaxReconsumeTimes() {
        return maxReconsumeTimes;
    }

    public void setMaxReconsumeTimes(final Integer maxReconsumeTimes) {
        this.maxReconsumeTimes = maxReconsumeTimes;
    }

    @Override
    public String toString() {
        return "ConsumerSendMsgBackRequestHeader [group=" + group + ", originTopic=" + originTopic + ", originMsgId=" + originMsgId
            + ", delayLevel=" + delayLevel + ", unitMode=" + unitMode + ", maxReconsumeTimes=" + maxReconsumeTimes + "]";
    }
}
