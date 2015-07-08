package com.kinztech.os.network.codec.login;

/**
 * Created by Allen Kinzalow on 5/23/2015.
 */
public enum LoginResponse {

    DIFF_WORLD(-1),
    //CONTINUE(0),
    CONTINUE(2),
    INVALID_USER_PASS(3),
    ACCOUNT_DISABLED(4),
    ALREADY_LOGGED_IN(5),
    RS_UPDATED(6),
    WORLD_FULL(7),
    LOGIN_SERVER_OFFLINE(8),
    LOGIN_LIMIT_EXCEEDED(9),
    BAD_SESSION_ID(10),
    SUSPECTED_ACCOUNT_COMP(11),
    NEED_MEMBER_ACC(12),
    INCOMPLETE_LOGIN(13),
    SERVER_BEING_UPDATED(14),
    TOO_MANY_ATTEMPTS(16),
    STANDING_IN_MEMBERS(17),
    ACCOUNT_LOCKED(18),
    CLOSED_BETA(19),
    INVALID_LOGIN_SERVER(20),
    MALFORMED_LOGIN_PACKET(22),
    NO_RESPONSE(23),
    ERROR_LOADING_PROFILE(24),
    UNEXPECTED_LS_RESPONSE(25),
    COMPUTER_ADDRESS_BLOCKED(26),
    SERVICE_UNAVAIBLE(27),
    DISPLAY_NAME_REQUIRED(31),
    CONTACT_BILLING(32),
    ACCOUNT_INACCESSIBLE(37),
    VOTE_REQUIRED(38),
    ACCOUNT_INELIGIBLE(55),
    ENTER_SIX_DIGIT_CODE(56),
    INCORRECT_CODE(57);


    int responseId;

    LoginResponse(int responseId) {
        this.responseId = responseId;
    }

    public int getResponseId() {
        return responseId;
    }

}
