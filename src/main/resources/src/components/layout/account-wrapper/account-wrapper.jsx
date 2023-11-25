import AccountNav from "../navigation/account-nav";
import ProfileContent from "../../blocks/profile-content/profile-content";
import "./account-wrapper.css";

function AccountWrapper() {
    return (
        <div className="account-wrapper">
            <AccountNav/>
            <ProfileContent/>
        </div>
    );
}

export default AccountWrapper;