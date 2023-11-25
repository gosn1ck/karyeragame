import LogoLink from "../../ui/logo-link/logo-link";
import NavList from "../../ui/nav-list/nav-list";
import "./account-nav.css";

function AccountNav() {
    return (
        <div class="account-nav">
            <LogoLink />
            <nav>
            <NavList />
            <div className="space"></div>
            <NavList />
            </nav>
        </div>
    );
}

export default AccountNav;