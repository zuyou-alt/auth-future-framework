package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hzy
 * @since 2024-02-01
 */
@TableName("area_info_5")
public class AreaInfo5 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("province_Code")
    private String provinceCode;

    @TableField("province_name")
    private String provinceName;

    @TableField("city_Code")
    private String cityCode;

    @TableField("city_name")
    private String cityName;

    @TableField("area_Code")
    private String areaCode;

    @TableField("area_name")
    private String areaName;

    @TableField("street_Code")
    private String streetCode;

    @TableField("street_name")
    private String streetName;

    @TableField("village_code")
    private String villageCode;

    @TableField("village_name")
    private String villageName;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    @Override
    public String toString() {
        return "AreaInfo5{" +
            "provinceCode=" + provinceCode +
            ", provinceName=" + provinceName +
            ", cityCode=" + cityCode +
            ", cityName=" + cityName +
            ", areaCode=" + areaCode +
            ", areaName=" + areaName +
            ", streetCode=" + streetCode +
            ", streetName=" + streetName +
            ", villageCode=" + villageCode +
            ", villageName=" + villageName +
        "}";
    }
}
