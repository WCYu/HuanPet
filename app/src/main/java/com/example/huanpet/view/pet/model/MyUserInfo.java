package com.example.huanpet.view.pet.model;

/**
 * Created by Administrator on 2017/12/17.
 */

public class MyUserInfo {


    /**
     * ret : true
     * result : {"birthday":1262275200000,"isUse":0,"userPhone":369963,"password":"4B73460AE8639EB8E0D5710D5EC4D662","id":0,"state":0,"qq":0,"userSex":1,"address":"北京，北京市，昌平区S","userName":"7655","userId":"4cb9f75e635d4e84b8570f3464efc907","position":1}
     */

    private boolean ret;
    private ResultBean result;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * birthday : 1262275200000
         * isUse : 0
         * userPhone : 369963
         * password : 4B73460AE8639EB8E0D5710D5EC4D662
         * id : 0
         * state : 0
         * qq : 0
         * userSex : 1
         * address : 北京，北京市，昌平区S
         * userName : 7655
         * userId : 4cb9f75e635d4e84b8570f3464efc907
         * position : 1
         */

        private long birthday;
        private int isUse;
        private long userPhone;
        private String password;
        private int id;
        private int state;
        private Long qq;
        private int userSex;
        private String address;
        private String userName;
        private String userId;
        private int position;
        private String identify;
        private String userImage;
        private String city;

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public long getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(long userPhone) {
            this.userPhone = userPhone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getQq() {
            return qq;
        }

        public void setQq(long qq) {
            this.qq = qq;
        }

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getIdentify() {
            return identify;
        }

        public void setIdentify(String identify) {
            this.identify = identify;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getCity(){
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
