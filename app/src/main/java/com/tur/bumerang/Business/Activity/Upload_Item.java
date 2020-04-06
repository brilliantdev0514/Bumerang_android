package com.yinji.bumerang.Business.Activity;

public class Upload_Item {
    public String edit_event;
    public String delet_event;
    public String fab_event;
    public String img_event;

    public Upload_Item(){

    }

    public Upload_Item(String _edit_event, String _delete_event, String _fab_event, String _img_event ){
        this.edit_event=_edit_event;
        this.delet_event=_delete_event;
        this.fab_event = _fab_event;
        this.img_event = _img_event;
    }

    public String getEdit_event(){ return edit_event;}
    public String getDelet_event(){ return delet_event;}
    public String getFab_event(){ return fab_event;}
    public String getImg_event(){ return img_event;}
}
