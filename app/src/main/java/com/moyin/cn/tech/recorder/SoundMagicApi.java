package com.moyin.cn.tech.recorder;

/**
 * Date: 2017/6/20
 * Author：HeChangPeng
 * Describe：魔音工具类
 */

public class SoundMagicApi {
   /* private static class Holder {
        public static final SoundMagicApi INSTANCE = new SoundMagicApi();
    }

    private SoundMagicApi() {

    }

    public static SoundMagicApi getInstance() {
        return Holder.INSTANCE;
    }*/

    //音效类型
    public static final int MODE_NORMAL = 0;
    public static final int MODE_LUOLI = 1;
    public static final int MODE_DASHU = 2;
    public static final int MODE_JINGSONG = 3;
    public static final int MODE_GAOGUAI = 4;
    public static final int MODE_KONGLING = 5;

    /**
     * @param path 音频文件路径
     * @param type 音效类型
     */
    public native static void changeSound(String path, int type);

    static {
        System.loadLibrary("fmodL");
        System.loadLibrary("fmod");
        System.loadLibrary("sound");
    }
}
