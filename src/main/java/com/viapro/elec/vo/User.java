package com.viapro.elec.vo;

/**
 * Created with IntelliJ IDEA.
 * User: N2048
 * Date: 13-8-21
 * Time: AM 10:37
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private String name;
    private String password;
    private String checkNumber;
    private String remeberMe;

    public String getRemeberMe() {
			return remeberMe;
		}

		public void setRemeberMe(String remeberMe) {
			this.remeberMe = remeberMe;
		}

		public String getCheckNumber() {
			return checkNumber;
		}

		public void setCheckNumber(String checkNumber) {
			this.checkNumber = checkNumber;
		}

		public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
