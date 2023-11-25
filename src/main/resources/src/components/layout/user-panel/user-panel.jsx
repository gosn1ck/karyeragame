import Avatar from "../../ui/avatar/avatar";
import Username from "../../ui/username/username";
import './user-panel.css';

import iconExit from "../../../assets/icons/icon_exit.svg";

function UserPanel() {
    return (
        <div className="user-panel">
            <Avatar/>
            <Username/>
            <a href="/">
                <img src={iconExit} className="user-panel__icon-exit" alt={"Выход"}/>
            </a>
        </div>
    );
}

export default UserPanel;