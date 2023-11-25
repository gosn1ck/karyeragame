import './button.css';

function ButtonWithIcon({className, text, icon}) {
    return (
        <button className={className}>
            <img src={icon} />
            <span className="btn-edit__text">{text}</span>
        </button>
    );
}

export default ButtonWithIcon;