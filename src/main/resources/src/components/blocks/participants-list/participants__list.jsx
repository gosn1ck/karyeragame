import './participants__list.css';

const participants = [
    {
        id: 1,
        name: "Участник 1"
    },
    {
        id: 2,
        name: "Участник 2"
    },
    {
        id: 3,
        name: "Участник 3"
    },
    {
        id: 4,
        name: "Участник 4"
    },
    {
        id: 5,
        name: "Участник 5"
    },
    {
        id: 6,
        name: "Участник 6"
    },
    {
        id: 7,
        name: "Участник 7"
    },
    {
        id: 8,
        name: "Участник 8"
    },
    {
        id: 9,
        name: "Участник 9"
    }
];

function Participants__list() {
    return (
        <div className="participants">
            {participants?.length ? (
                <>
                    <ul className="participants__list">
                        {participants.map((participant) => (
                            <li key={participant.id}>
                                <div className="participant__wrapper">
                                    <div className="participants__avatar"></div>
                                    <span className="participants__username">{participant.name}</span>
                                </div>
                            </li>
                        ))}
                    </ul>
                </>
            ) : null}
        </div>
    );
}

export default Participants__list;