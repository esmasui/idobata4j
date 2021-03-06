/*
 * Copyright (C) 2014 uPhyca Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uphyca.idobata;

import com.uphyca.idobata.event.ConnectionEvent;
import com.uphyca.idobata.event.MemberStatusChangedEvent;
import com.uphyca.idobata.event.MessageCreatedEvent;
import com.uphyca.idobata.event.RoomTouchedEvent;

import java.io.Closeable;

/**
 * Represents <a href="https://idobata.io/">Idobata</a> WebSocket API.
 * 
 * @author Sosuke Masui (masui@uphyca.com)
 */
public interface IdobataStream extends Closeable {

    public interface Listener<T> {
        void onEvent(T event);
    }

    public interface ConnectionListener {
        void closed(ConnectionEvent event);

        void opened(ConnectionEvent event);
    }

    void open();

    /**
     * event: message_created
     */
    IdobataStream subscribeMessageCreated(Listener<MessageCreatedEvent> listener);

    /**
     * event: member_status_changed
     */
    IdobataStream subscribeMemberStatusChanged(Listener<MemberStatusChangedEvent> listener);

    /**
     * event: room_touched
     */
    IdobataStream subscribeRoomTouched(Listener<RoomTouchedEvent> listener);

    IdobataStream setErrorListener(ErrorListener listener);

    IdobataStream setConnectionListener(ConnectionListener listener);
}
