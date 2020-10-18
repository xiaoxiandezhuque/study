package com.xh.databinding;

//public class UserBean extends BaseObservable {
//    private String name;
//
//    @Bindable
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//        notifyPropertyChanged(BR.name);
//    }
//}


import androidx.databinding.ObservableField;

public class UserBean {
    private ObservableField<String> name = new ObservableField<>();

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
