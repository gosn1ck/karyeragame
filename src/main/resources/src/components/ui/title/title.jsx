import './title.css';

export const TitleSize = {
    BIG: "big",
    MEDIUM: "medium",
    SMALL: "small",
    DEFAULT: ""
};

function Title ({ title, size }) {
    return <h1 className={`title${size ? ` title_${size}` : ""}`}>{title}</h1>;
}

export default Title;