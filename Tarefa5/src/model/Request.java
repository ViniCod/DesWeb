package model;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
	private String req;
	private String ses;
	private String app;

	public Request() {
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getSes() {
		return ses;
	}

	public void setSes(String ses) {
		this.ses = ses;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "Request [req=" + req + ", ses=" + ses + ", app=" + app + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (req == null) {
			if (other.req != null)
				return false;
		} else if (!req.equals(other.req))
			return false;
		if (ses == null) {
			if (other.ses != null)
				return false;
		} else if (!ses.equals(other.ses))
			return false;
		if (app == null) {
			if (other.app != null)
				return false;
		} else if (!app.equals(other.app))
			return false;
		return true;
	}
}
