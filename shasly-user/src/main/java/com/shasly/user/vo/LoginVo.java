package com.shasly.user.vo;/*
 *________________********_______________________
 *______________************_____________________
 *______________*************____________________
 *_____________**__***********___________________
 *____________***__******_*****__________________
 *____________***_*******___****_________________
 *___________***__**********_****________________
 *__________****__***********_****_______________
 *________*****___***********__*****_____________
 *_______******___***_********___*****___________
 *_______*****___***___********___******_________
 *______******___***__***********___******_______
 *_____******___****_**************__******______
 *____*******__***** ***************_ ******_____
 *____*******__***** ****************_ ******____
 *___*******__******_*****************_*******___
 *___*******__******_******_*********___******___
 *___*******____**__******___******_____******___
 *___*******________******____*****_____*****____
 *____******________*****_____*****_____****_____
 *_____*****________****______*****_____***______
 *______*****______;***________***______*________
 *________**_______****________****______________
 *
 *          初闻天籁之音,未使心之将来——初音未来
 *
 */

public class LoginVo {
    private String username ;
    private String password ;
    private String vcode ;
    private String auto ;

    public LoginVo() {

    }

    public LoginVo(String username, String password, String vcode, String auto) {
        this.username = username;
        this.password = password;
        this.vcode = vcode;
        this.auto = auto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vcode='" + vcode + '\'' +
                ", auto='" + auto + '\'' +
                '}';
    }
}
