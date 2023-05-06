package com.jhd.dotime.chat.common.exception;

import com.jhd.dotime.chat.common.error.ChatRoomErrorCode;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import lombok.Getter;

@Getter
public class ChatException extends RuntimeException{

        private final ChatRoomErrorCode errorCode;

        public ChatException(ChatRoomErrorCode errorCode, Throwable reason){
            super(errorCode.getMessage(), reason);
            this.errorCode = errorCode;
        }

        public ChatException(ChatRoomErrorCode errorCode){
            super(errorCode.getMessage());
            this.errorCode = errorCode;
        }
    }
