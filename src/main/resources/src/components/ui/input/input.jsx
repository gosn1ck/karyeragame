import './input.css';

function Input(label, id, name, type, size, disabled) {
    return (
        <>
            <h4 className="input__label">{label}</h4>
            <input id={id} name={name} className="input-field" size={size} disabled={disabled}/>
        </>
    );
}

export default Input;