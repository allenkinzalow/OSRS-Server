package com.kinztech.os.game.node.entity.player.varp;

import com.kinztech.os.game.node.entity.player.Player;
import com.kinztech.os.network.codec.game.encode.VarpEncoder;

import java.util.HashMap;

/**
 * Created by Allen Kinzalow on 5/25/2015.
 */
public class VarpContainer {

    /**
     * The varp map - used to reference
     */
    private final HashMap<Integer,Varp> varpMap = new HashMap<Integer,Varp>();

    /**
     * The player containing the varps
     */
    private Player player;

    public VarpContainer(Player player) {
        this.player = player;
    }

    /**
     * Add varps required on login - TODO: not this method of delivery
     */
    public void addInitialVarp() {
        /**
         * Just testing, not permanent
         */
        varpMap.put(0, new Varp(0 , 11));
        varpMap.put(5, new Varp(5 , 10));
        varpMap.put(10, new Varp(10 , 8));
        varpMap.put(11, new Varp(11 , 5));
        varpMap.put(12, new Varp(12 , 16));
        varpMap.put(14, new Varp(14 , 7));
        varpMap.put(17, new Varp(17 , 15));
        varpMap.put(18, new Varp(18 , 1));
        varpMap.put(21, new Varp(21 , -1));
        varpMap.put(26, new Varp(26 , 90));
        varpMap.put(29, new Varp(29 , 2));
        varpMap.put(30, new Varp(30 , 80));
        varpMap.put(31, new Varp(31 , 100));
        varpMap.put(32, new Varp(32 , 3));
        varpMap.put(63, new Varp(63 , 6));
        varpMap.put(65, new Varp(65 , 10));
        varpMap.put(67, new Varp(67 , 3));
        varpMap.put(68, new Varp(68 , 16));
        varpMap.put(71, new Varp(71 , 4));
        varpMap.put(76, new Varp(76 , 6));
        varpMap.put(80, new Varp(80 , 4));
        varpMap.put(107, new Varp(107 , 5));
        varpMap.put(111, new Varp(111 , 9));
        varpMap.put(116, new Varp(116 , 17));
        varpMap.put(122, new Varp(122 , 7));
        varpMap.put(130, new Varp(130 , 4));
        varpMap.put(131, new Varp(131 , 9));
        varpMap.put(139, new Varp(139 , 1));
        varpMap.put(144, new Varp(144 , 100));
        varpMap.put(146, new Varp(146 , 4));
        varpMap.put(147, new Varp(147 , 6));
        varpMap.put(148, new Varp(148 , 11));
        varpMap.put(159, new Varp(159 , 12));
        varpMap.put(160, new Varp(160 , 2));
        varpMap.put(161, new Varp(161 , 10));
        varpMap.put(165, new Varp(165 , 30));
        varpMap.put(166, new Varp(166 , 4));
        varpMap.put(167, new Varp(167 , 0));
        varpMap.put(168, new Varp(168 , 4));
        varpMap.put(169, new Varp(169 , 4));
        varpMap.put(170, new Varp(170 , 0));
        varpMap.put(171, new Varp(171 , 1));
        varpMap.put(172, new Varp(172 , 1));
        varpMap.put(173, new Varp(173 , 1));
        varpMap.put(175, new Varp(175 , 12));
        varpMap.put(176, new Varp(176 , 10));
        varpMap.put(178, new Varp(178 , 3));
        varpMap.put(179, new Varp(179 , 21));
        varpMap.put(180, new Varp(180 , 6));
        varpMap.put(188, new Varp(188 , 15));
        varpMap.put(192, new Varp(192 , 2));
        varpMap.put(197, new Varp(197 , 30));
        varpMap.put(200, new Varp(200 , 5));
        varpMap.put(212, new Varp(212 , 14));
        varpMap.put(223, new Varp(223 , 9));
        varpMap.put(226, new Varp(226 , 7));
        varpMap.put(287, new Varp(287 , 1));
        varpMap.put(293, new Varp(293 , 65));
        varpMap.put(302, new Varp(302 , 61));
        varpMap.put(307, new Varp(307 , 110));
        varpMap.put(309, new Varp(309 , 1));
        varpMap.put(314, new Varp(314 , 80));
        varpMap.put(317, new Varp(317 , 50));
        varpMap.put(318, new Varp(318 , 63));
        varpMap.put(320, new Varp(320 , 3));
        varpMap.put(328, new Varp(328 , 15));
        varpMap.put(335, new Varp(335 , 110));
        varpMap.put(347, new Varp(347 , 10));
        varpMap.put(365, new Varp(365 , 10));
        varpMap.put(382, new Varp(382 , 11));
        varpMap.put(387, new Varp(387 , 110));
        varpMap.put(399, new Varp(399 , 9));
        varpMap.put(402, new Varp(402 , 6));
        varpMap.put(403, new Varp(403 , 30));
        varpMap.put(406, new Varp(406 , 20));
        varpMap.put(447, new Varp(447 , -1));
        varpMap.put(477, new Varp(477 , 7));
        varpMap.put(492, new Varp(492 , 4));
        varpMap.put(508, new Varp(508 , 12));
        varpMap.put(600, new Varp(600 , 19));
        varpMap.put(661, new Varp(661 , 1));
        varpMap.put(667, new Varp(667 , 42));
        varpMap.put(673, new Varp(673 , 2));
        varpMap.put(681, new Varp(681 , 60));
        varpMap.put(700, new Varp(700 , 1));
        varpMap.put(714, new Varp(714 , 4));
        varpMap.put(728, new Varp(728 , 6));
        varpMap.put(802, new Varp(802 , 15));
        varpMap.put(816, new Varp(816 , 56));
        varpMap.put(842, new Varp(842 , 1));
        varpMap.put(849, new Varp(849 , -1));
        varpMap.put(850, new Varp(850 , -1));
        varpMap.put(851, new Varp(851 , -1));
        varpMap.put(852, new Varp(852 , -1));
        varpMap.put(853, new Varp(853 , -1));
        varpMap.put(854, new Varp(854 , -1));
        varpMap.put(855, new Varp(855 , -1));
        varpMap.put(856, new Varp(856 , -1));
        varpMap.put(872, new Varp(872 , 4));
        varpMap.put(942, new Varp(942 , 8));
        varpMap.put(951, new Varp(951 , -1));
        varpMap.put(952, new Varp(952 , -1));
        varpMap.put(953, new Varp(953 , -1));
        varpMap.put(954, new Varp(954 , -1));
        varpMap.put(955, new Varp(955 , -1));
        varpMap.put(956, new Varp(956 , -1));
        varpMap.put(959, new Varp(959 , -1));
        varpMap.put(960, new Varp(960 , -1));
        varpMap.put(961, new Varp(961 , -1));
        varpMap.put(962, new Varp(962 , -1));
        varpMap.put(963, new Varp(963 , -1));
        varpMap.put(983, new Varp(983 , 1));
        varpMap.put(987, new Varp(987 , 2));
        varpMap.put(994, new Varp(994 , 80));
        varpMap.put(1052, new Varp(1052 , 1));
        varpMap.put(1055, new Varp(1055 , 64));
        varpMap.put(1065, new Varp(1065 , -1));
        varpMap.put(1074, new Varp(1074 , 1));
        varpMap.put(1075, new Varp(1075 , -1));
        varpMap.put(1107, new Varp(1107 , 0));
        varpMap.put(1141, new Varp(1141 , 3));
        varpMap.put(1151, new Varp(1151 , -1));

        varpMap.put(20, new Varp(20 , -20545));
        varpMap.put(22, new Varp(22 , 2147483647));
        varpMap.put(23, new Varp(23 , -805306498));
        varpMap.put(24, new Varp(24 , -2143289345));
        varpMap.put(25, new Varp(25 , -134234113));
        varpMap.put(62, new Varp(62 , 454));
        varpMap.put(84, new Varp(84 , 8192));
        varpMap.put(101, new Varp(101 , 198));
        varpMap.put(135, new Varp(135 , 1010));
        varpMap.put(150, new Varp(150 , 160));
        varpMap.put(162, new Varp(162 , 15727614));
        varpMap.put(177, new Varp(177 , 8257604));
        varpMap.put(222, new Varp(222 , 22551075));
        varpMap.put(279, new Varp(279 , 63489));
        varpMap.put(281, new Varp(281 , 1000));
        varpMap.put(298, new Varp(298 , 2011922422));
        varpMap.put(299, new Varp(299 , 1095422));
        varpMap.put(300, new Varp(300 , 1000));
        varpMap.put(304, new Varp(304 , 2580000));
        varpMap.put(311, new Varp(311 , -1115684897));
        varpMap.put(313, new Varp(313 , 732));
        varpMap.put(346, new Varp(346 , 536856287));
        varpMap.put(351, new Varp(351 , 33552394));
        varpMap.put(361, new Varp(361 , 8388608));
        varpMap.put(362, new Varp(362 , -2147483648));
        varpMap.put(372, new Varp(372 , 90663863));
        varpMap.put(383, new Varp(383 , 2098759));
        varpMap.put(408, new Varp(408 , -2017034368));
        varpMap.put(414, new Varp(414 , -67301501));
        varpMap.put(416, new Varp(416 , 285));
        varpMap.put(417, new Varp(417 , 1182793726));
        varpMap.put(423, new Varp(423 , -807765690));
        varpMap.put(425, new Varp(425 , 1033));
        varpMap.put(435, new Varp(435 , 693532));
        varpMap.put(436, new Varp(436 , 855699855));
        varpMap.put(437, new Varp(437 , 413290));
        varpMap.put(440, new Varp(440 , 1540111));
        varpMap.put(441, new Varp(441 , -971504863));
        varpMap.put(442, new Varp(442 , -449958233));
        varpMap.put(443, new Varp(443 , 25165824));
        varpMap.put(449, new Varp(449 , 786432010));
        varpMap.put(452, new Varp(452 , 2867336));
        varpMap.put(453, new Varp(453 , 1310784000));
        varpMap.put(455, new Varp(455 , -1908818002));
        varpMap.put(464, new Varp(464 , -1057357858));
        varpMap.put(465, new Varp(465 , 17588235));
        varpMap.put(482, new Varp(482 , 389219250));
        varpMap.put(486, new Varp(486 , 1073741824));
        varpMap.put(491, new Varp(491 , 1073856512));
        varpMap.put(496, new Varp(496 , 7569410));
        varpMap.put(498, new Varp(498 , 100663296));
        varpMap.put(503, new Varp(503 , 570425344));
        varpMap.put(506, new Varp(506 , 3473408));
        varpMap.put(511, new Varp(511 , 50331742));
        varpMap.put(512, new Varp(512 , 570425344));
        varpMap.put(515, new Varp(515 , 171));
        varpMap.put(516, new Varp(516 , 16777216));
        varpMap.put(520, new Varp(520 , 12289));
        varpMap.put(521, new Varp(521 , -208678004));
        varpMap.put(531, new Varp(531 , 787));
        varpMap.put(534, new Varp(534 , 153391689));
        varpMap.put(540, new Varp(540 , 1051908));
        varpMap.put(553, new Varp(553 , -730397316));
        varpMap.put(554, new Varp(554 , 1609826303));
        varpMap.put(571, new Varp(571 , -1565609973));
        varpMap.put(572, new Varp(572 , 50333696));
        varpMap.put(598, new Varp(598 , 2081193735));
        varpMap.put(601, new Varp(601 , 133169344));
        varpMap.put(602, new Varp(602 , -7837059));
        varpMap.put(604, new Varp(604 , 1060132));
        varpMap.put(607, new Varp(607 , 15728640));
        varpMap.put(615, new Varp(615 , 12917079));
        varpMap.put(616, new Varp(616 , 129212));
        varpMap.put(623, new Varp(623 , 402697352));
        varpMap.put(641, new Varp(641 , -2146995258));
        varpMap.put(642, new Varp(642 , -633346049));
        varpMap.put(643, new Varp(643 , 1611661283));
        varpMap.put(655, new Varp(655 , 140));
        varpMap.put(657, new Varp(657 , 327680));
        varpMap.put(662, new Varp(662 , 402908287));
        varpMap.put(671, new Varp(671 , 25165914));
        varpMap.put(678, new Varp(678 , 583024645));
        varpMap.put(679, new Varp(679 , -677293824));
        varpMap.put(680, new Varp(680 , -1585644291));
        varpMap.put(683, new Varp(683 , 20590));
        varpMap.put(684, new Varp(684 , 1621579088));
        varpMap.put(685, new Varp(685 , 1275068466));
        varpMap.put(704, new Varp(704 , -1880454148));
        varpMap.put(705, new Varp(705 , 373249454));
        varpMap.put(715, new Varp(715 , 32894));
        varpMap.put(716, new Varp(716 , 538738852));
        varpMap.put(721, new Varp(721 , -337384832));
        varpMap.put(723, new Varp(723 , 266205384));
        varpMap.put(738, new Varp(738 , 350554145));
        varpMap.put(788, new Varp(788 , 184));
        varpMap.put(794, new Varp(794 , 7534605));
        varpMap.put(810, new Varp(810 , 60082984));
        varpMap.put(830, new Varp(830 , 4194304));
        varpMap.put(844, new Varp(844 , 22368316));
        varpMap.put(845, new Varp(845 , 57671680));
        varpMap.put(867, new Varp(867 , 19926027));
        varpMap.put(869, new Varp(869 , 71626560));
        varpMap.put(870, new Varp(870 , 1431970062));
        varpMap.put(874, new Varp(874 , -1040318706));
        varpMap.put(896, new Varp(896 , 493162603));
        varpMap.put(897, new Varp(897 , -1610478016));
        varpMap.put(904, new Varp(904 , 246));
        varpMap.put(906, new Varp(906 , 2046574207));
        varpMap.put(907, new Varp(907 , 3204));
        varpMap.put(913, new Varp(913 , 4194304));
        varpMap.put(934, new Varp(934 , -8994776));
        varpMap.put(935, new Varp(935 , 488));
        varpMap.put(936, new Varp(936 , 1072339444));
        varpMap.put(939, new Varp(939 , 240));
        varpMap.put(968, new Varp(968 , 320275335));
        varpMap.put(970, new Varp(970 , 69992788));
        varpMap.put(971, new Varp(971 , 128));
        varpMap.put(977, new Varp(977 , 1362));
        varpMap.put(978, new Varp(978 , 130023424));
        varpMap.put(980, new Varp(980 , 130));
        varpMap.put(981, new Varp(981 , 5278384));
        varpMap.put(982, new Varp(982 , 671613056));
        varpMap.put(990, new Varp(990 , -2146959351));
        varpMap.put(991, new Varp(991 , 537395200));
        varpMap.put(992, new Varp(992 , 41366));
        varpMap.put(995, new Varp(995 , 100200));
        varpMap.put(996, new Varp(996 , 446));
        varpMap.put(997, new Varp(997 , 240472075));
        varpMap.put(1000, new Varp(1000 , -1342161923));
        varpMap.put(1001, new Varp(1001 , 1073741830));
        varpMap.put(1002, new Varp(1002 , 536870916));
        varpMap.put(1009, new Varp(1009 , 537657860));
        varpMap.put(1010, new Varp(1010 , 2103));
        varpMap.put(1011, new Varp(1011 , 2028675));
        varpMap.put(1017, new Varp(1017 , 8192));
        varpMap.put(1045, new Varp(1045 , 1883118596));
        varpMap.put(1046, new Varp(1046 , 1913061430));
        varpMap.put(1047, new Varp(1047 , 7168));
        varpMap.put(1049, new Varp(1049 , 1114));
        varpMap.put(1050, new Varp(1050 , 4097));
        varpMap.put(1058, new Varp(1058 , 65024));
        varpMap.put(1059, new Varp(1059 , 18874368));
        varpMap.put(1060, new Varp(1060 , 16306));
        varpMap.put(1067, new Varp(1067 , -1752694784));
        varpMap.put(1143, new Varp(1143 , 241660));
        varpMap.put(869, new Varp(869 , 71626560));
        sendAllVarps();
    }

    public void sendAllVarps() {
        for(Varp varp : varpMap.values())
            sendVarp(varp);
    }

    /**
     * Dispatch a varp
     * @param
     */
    public void sendVarp(int id, int value) {
        if(varpMap.get(id) == null)
            varpMap.put(id, new Varp(id, value)); // stores the varp as a way to reference it.
        Varp varp = varpMap.get(id);
        varp.setValue(value);
        player.write(new VarpEncoder(varp));
    }

    public void sendVarp(Varp varp) {
        if(varpMap.get(varp.getId()) == null)
            varpMap.put(varp.getId(), varp);
        player.write(new VarpEncoder(varp));
    }

    /**
     * Retrieves the varp map.
     * @return
     */
    public HashMap<Integer,Varp> getVarpMap() {
        return varpMap;
    }


}
