package home_assignment;

class LibraryMemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMemberBean() {
        this(null, null);
    }

    public LibraryMemberBean(String name) {
        this(null, name);
    }

    public LibraryMemberBean(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            securityAnswer = String.valueOf(answer.hashCode());
        }
    }
}

public class F4_LibraryMemberBean {
    public static void main(String[] args) {
        LibraryMemberBean member = new LibraryMemberBean();

        member.setMembershipId("LIB-8841");
        member.setMembershipId("FAKE-0000");

        System.out.println(member.getMembershipId());
    }
}
