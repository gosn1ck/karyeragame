import logo from "../../../assets/img/logo.png";
import "./logo-link.css";

function LogoLink () {
    return (
        <a href="/" className="logo-link">
            <div className="logo-wrapper">
                <img src={logo} className="logo"/>
                <span className="logo__title">ПС КарьерА</span>
            </div>
        </a>
    );
}

export default LogoLink;