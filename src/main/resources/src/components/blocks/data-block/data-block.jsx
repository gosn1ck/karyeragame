import './data-block.css'

function DataBlock({button,  title, children }) {
    return (
        <div className="data-block">
            <div className="data-block__header">
            <span className="data-block__title">{title}</span>
                {button ? button : ""}
            </div>
            {children}
        </div>
    );
}

export default DataBlock;
