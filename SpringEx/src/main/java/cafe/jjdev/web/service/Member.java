package cafe.jjdev.web.service;

public class Member {
	private int MemberNo;
	private String MemberId;
	private String MemberPw;
	private String MemberName;
	
	public int getMemberNo() {
		return MemberNo;
	}
	public void setMemberNo(int memberNo) {
		MemberNo = memberNo;
	}
	public String getMemberId() {
		return MemberId;
	}
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	public String getMemberPw() {
		return MemberPw;
	}
	public void setMemberPw(String memberPw) {
		MemberPw = memberPw;
	}
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	
	@Override
	public String toString() {
		return "Member [MemberNo=" + MemberNo + ", MemberId=" + MemberId + ", MemberPw=" + MemberPw + ", MemberName="
				+ MemberName + "]";
	}
	
	
}
