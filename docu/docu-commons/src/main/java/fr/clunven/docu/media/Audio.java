package fr.clunven.docu.media;

/**
 * Identifier for Audio metadata.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public enum Audio {

    ID_STRING("ID/String"),
    UNIQUEID_STRING("UniqueID/String"),
    MENUID_STRING("MenuID/String"),
    FORMAT("Format"),
    FORMAT_INFO("Format/Info"),
    FORMAT_COMMERCIAL_IFANY("Format_Commercial_IfAny"),
    FORMAT_VERSION("Format_Version"),
    FORMAT_PROFILE("Format_Profile"),
    FORMAT_COMPRESSION("Format_Compression"),
    FORMAT_SETTINGS_MODE("Format_Settings_Mode"),
    FORMAT_SETTINGS_MODEEXTENSION("Format_Settings_ModeExtension"),
    FORMAT_SETTINGS_EMPHASIS("Format_Settings_Emphasis"),
    FORMAT_SETTINGS_FLOOR("Format_Settings_Floor"),
    FORMAT_SETTINGS_FIRM("Format_Settings_Firm"),
    FORMAT_SETTINGS_ENDIANNESS("Format_Settings_Endianness"),
    FORMAT_SETTINGS_SIGN("Format_Settings_Sign"),
    FORMAT_SETTINGS_LAW("Format_Settings_Law"),
    FORMAT_SETTINGS_ITU("Format_Settings_ITU"),
    FORMAT_SETTINGS_WRAPPING("Format_Settings_Wrapping"),
    MATRIX_FORMAT("Matrix_Format"),
    MUXINGMODE("MuxingMode"),
    MUXINGMODE_MOREINFO("MuxingMode_MoreInfo"),
    CODECID("CodecID"),
    CODECID_STRING("CodecID/String"),
    CODECID_INFO("CodecID/Info"),
    CODECID_HINT("CodecID/Hint"),
    CODECID_DESCRIPTION("CodecID_Description"),
    DURATION_STRING("Duration/String"),
    DURATION_FIRSTFRAME_STRING("Duration_FirstFrame/String"),
    DURATION_LASTFRAME_STRING("Duration_LastFrame/String"),
    SOURCE_DURATION_STRING("Source_Duration/String"),
    SOURCE_DURATION_FIRSTFRAME_STRING("Source_Duration_FirstFrame/String"),
    SOURCE_DURATION_LASTFRAME_STRING("Source_Duration_LastFrame/String"),
    BITRATE("BitRate/String"),
    BITRATE_MINIMUM_STRING("BitRate_Minimum/String"),
    BITRATE_NOMINAL_STRING("BitRate_Nominal/String"),
    BITRATE_MAXIMUM_STRING("BitRate_Maximum/String"),
    BITRATE_ENCODED_STRING("BitRate_Encoded/String"),
    BITRATE_MODE_STRING("BitRate_Mode/String"),
    CHANNEL_STRING("Channel(s)/String"),
    CHANNEL_ORIGINAL_STRING("Channel(s)_Original/String"),
    MATRIX_CHANNEL_STRING("Matrix_Channel(s)/String"),
    CHANNELPOSITIONS("ChannelPositions"),
    MATRIX_CHANNELPOSITIONS("Matrix_ChannelPositions"),
    SAMPLINGRATE_STRING("SamplingRate/String"),
    BITDEPTH_STRING("BitDepth/String"),
    BITDEPTH_STORED_STRING("BitDepth_Stored/String"),
    COMPRESSION_MODE_STRING("Compression_Mode/String"),
    COMPRESSION_RATIO("Compression_Ratio"),
    VIDEO_DELAY_STRING("Video_Delay/String"),
    REPLAYGAIN_GAIN_STRING("ReplayGain_Gain/String"),
    REPLAYGAIN_PEAK("ReplayGain_Peak"),
    STREAMSIZE_STRING("StreamSize/String"),
    SOURCE_STREAMSIZE_STRING("Source_StreamSize/String"),
    STREAMSIZE_ENCODED_STRING("StreamSize_Encoded/String"),
    SOURCE_STREAMSIZE_ENCODED_STRING("Source_StreamSize_Encoded/String"),
    ALIGNMENT_STRING("Alignment/String"),
    INTERLEAVE_DURATION_STRING("Interleave_Duration/String"),
    INTERLEAVE_PRELOAD_STRING("Interleave_Preload/String"),
    TITLE("Title"),
    ENCODED_APPLICATION_STRING("Encoded_Application/String"),
    ENCODED_LIBRARY_STRING("Encoded_Library/String"),
    ENCODED_LIBRARY_SETTINGS("Encoded_Library_Settings"),
    LANGUAGE_STRING("Language/String"),
    LANGUAGE_MORE("Language_More"),
    DEFAULT_STRING("Default/String"),
    FORCED_STRING("Forced/String"),
    ENCODED_DATE("Encoded_Date"),
    TAGGED_DATE("Tagged_Date"),
    ENCRYPTION("Encryption");
    
    /** key to retrieve correct attribute in the file. */
    private String key;
    
    /**
     * Constructor
     * @param key
     *      attribute key
     */
    private Audio(String key) {
        this.key = key;
    }
    
    /** Access to KEY. */
    public String getKey() {
        return key;
    }

}
