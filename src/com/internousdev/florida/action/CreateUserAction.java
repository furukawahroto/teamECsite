package com.internousdev.florida.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware {

	private String backFlag;
	private Map<String,Object> session;

	public String execute(){
		//セッション変数tempUserId(仮ID）が存在しなければ、セッションTIMEOUTを返し、sessionError.jspへ遷移
		if(!session.containsKey("tempUserId")){
			return "sessionTimeOut";
		}

		//createUserComfirm.jspからの遷移であればbackFlgを使用し、ユーザーが入力したセッション変数を保持するが、login.jspからの遷移（新規登録）であればセッション変数を削除する

		if(backFlag==null){
			session.remove("familyName");
			session.remove("firstName");
			session.remove("familyNameKana");
			session.remove("firstNameKana");
			session.remove("sex");
			session.remove("email");
			session.remove("userIdForCreateUser");
			session.remove("password");
		}

		//次画面の入力フォーム（性別）の前準備
		List<String> sexList=new ArrayList<String>();
		//セッション変数"sex"が存在していなければ、新たに値を"男性"として作成（login.jspから遷移した場合）
		if(!session.containsKey("sex")){
			session.put("sex","男性");
		//セッション変数"sex"が存在していれば、その値を取り出し、再び格納する（createUserComfirm.jspから戻るボタンが押下し、遷移した場合）
		}else{
			session.put("sex", String.valueOf(session.get("sex")));
		}
		//次画面で使用する性別の選択肢をセッション変数"sexList"として用意
		sexList.add("男性");
		sexList.add("女性");
		session.put("sexList", sexList);

		return SUCCESS;
	}

	public String getBackFlag(){
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String,Object> session){
		this.session=session;
	}
}
