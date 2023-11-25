import UserPanel from "../../layout/user-panel/user-panel";
import Title, {TitleSize} from "../../ui/title/title";
import DataBlock from "../data-block/data-block";
import UserData from "../user-data/user-data";
import GameData from "../game-data/game-data";
import Centering from "../../layout/centering/centering";
import Button from "../../ui/button/button";
import './profile-content.css';

function ProfileContent() {
    return (
        <div className="profile-content">
            <UserPanel/>
            <Title size={TitleSize.BIG}/>
            <DataBlock button={Button} title="Информация о пользователе" children={UserData}/>
            <DataBlock title="Информация об игре" children={GameData}/>
            <Centering children={<Button className={"btn-delete"} text={"Удалить аккаунт"}/>}/>
        </div>
    );
}

export default ProfileContent;