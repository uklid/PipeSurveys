package uklid.com.pipesurvey;

/**
 * Created by Uklid on 5/25/2015.
 */
public class PipeSurvey {
    private int _id;
    private String pipecode;
    private String sheetno;
    private String date;
    private String code;
    private String order;
    private String utilization;
    private String type;
    private String name;
    private String no;
    private String moo;
    private String soi;
    private String road;
    private String subdistrict;
    private String district;
    private String province;
    private String po;
    private String telephone;
    private int noccupants;
    private String foccupants;
    private int nstorey;
    private int nroom;
    private String material;
    private String construction;
    private String picturename;

    public PipeSurvey(int _id, String pipecode, String sheetno, String date, String code, String order, String utilization, String type, String name, String no, String moo, String soi, String road, String subdistrict, String district, String province, String po, String telephone, int noccupants, String foccupants, int nstorey, int nroom, String material, String construction, String picturename) {
        this._id = _id;
        this.pipecode = pipecode;
        this.sheetno = sheetno;
        this.date = date;
        this.code = code;
        this.order = order;
        this.utilization = utilization;
        this.type = type;
        this.name = name;
        this.no = no;
        this.moo = moo;
        this.soi = soi;
        this.road = road;
        this.subdistrict = subdistrict;
        this.district = district;
        this.province = province;
        this.po = po;
        this.telephone = telephone;
        this.noccupants = noccupants;
        this.foccupants = foccupants;
        this.nstorey = nstorey;
        this.nroom = nroom;
        this.material = material;
        this.construction = construction;
        this.picturename = picturename;
    }

    public int getId() {
        return _id;
    }

    public String getPipecode() {
        return pipecode;
    }

    public String getSheetno() {
        return sheetno;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getOrder() {
        return order;
    }

    public String getUtilization() {
        return utilization;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getNo() {
        return no;
    }

    public String getMoo() {
        return moo;
    }

    public String getSoi() {
        return soi;
    }

    public String getRoad() {
        return road;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public String getDistrict() {
        return district;
    }

    public String getProvince() {
        return province;
    }

    public String getPo() {
        return po;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getNoccupants() {
        return noccupants;
    }

    public String getFoccupants() {
        return foccupants;
    }

    public int getNstorey() {
        return nstorey;
    }

    public int getNroom() {
        return nroom;
    }

    public String getMaterial() {
        return material;
    }

    public String getConstruction() {
        return construction;
    }

    public String getPicturename() {
        return picturename;
    }
}
