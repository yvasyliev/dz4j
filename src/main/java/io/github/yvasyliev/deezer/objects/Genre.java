package io.github.yvasyliev.deezer.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.net.URL;
import java.util.List;

/**
 * A genre object.
 */
@Data
public class Genre implements Pageable {
    /**
     * The editorial's Deezer id.
     */
    @Expose
    @SerializedName("id")
    private Long id;

    /**
     * The editorial's name.
     */
    @Expose
    @SerializedName(value = "name", alternate = "title")
    private String name;

    /**
     * The url of the genre picture. Add 'size' parameter to the url to change size. Can be 'small', 'medium', 'big', 'xl'.
     */
    @Expose
    @SerializedName("picture")
    private Picture picture;

    /**
     * The url of the genre picture in size small.
     */
    @Expose
    @SerializedName("picture_small")
    private URL pictureSmall;

    /**
     * The url of the genre picture in size medium.
     */
    @Expose
    @SerializedName("picture_medium")
    private URL pictureMedium;

    /**
     * The url of the genre picture in size big.
     */
    @Expose
    @SerializedName("picture_big")
    private URL pictureBig;

    /**
     * The url of the genre picture in size xl.
     */
    @Expose
    @SerializedName("picture_xl")
    private URL pictureXl;

    /**
     * A list of radios for current genre.
     */
    @Expose
    @SerializedName("radios")
    private List<Radio> radios;
}
