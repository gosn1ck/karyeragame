import './nav-list.css';
import Profile from '../../../assets/icons/icon_profile.svg';

const menuItemsUser = [
    {
        id: 1,
        text: "Профиль",
        icon: {Profile},
    },
    {
        id: 2,
        text: "Остаток по счету",
        icon: "../../../assets/icons/icon_statement.svg",
    },
    {
        id: 3,
        text: "Выписка",
        icon: "../../../assets/icons/icon_balance.svg",
    },
    {
        id: 4,
        text: "Заплатить",
        icon: "../../../assets/icons/icon_pay.svg",
    }
];

function NavList() {
    return (
        <ul className="nav">
            {menuItemsUser.map((menuItem) => (
                <li>
                    <div className="nav__item nav__item--active">
                        <img src={Profile} className="nav__icon--active"/>
                        <a href="#" className="nav__link nav__link--active">{menuItem.text}</a>
                    </div>
                </li>
            ))}
        < /ul>
    );
}

export default NavList;