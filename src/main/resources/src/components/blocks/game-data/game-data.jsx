import Title, {TitleSize} from "../../ui/title/title";
import Participants__list from "../participants-list/participants__list";
import participants from "../../../mocks/participants";

function GameData() {
    return (
        <div className="game-data">
            <Title size={TitleSize.MEDIUM}>Участники</Title>
            <Participants__list participants={participants} />
        </div>
    );
}

export default GameData;