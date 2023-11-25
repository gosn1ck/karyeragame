import Input from "../../ui/input/input";
import './user-data.css';

function UserData() {
    return (
        <div className="user-data">
            <div className="user-data__block">
                <Input label={'Логин'} id={'login'} name={'login'} type={'text'} size={15} disabled={true}/>
                {/*<h4 className="input__label">Логин</h4>*/}
                {/*<input id="login" name="login" className="input-field" size="15" disabled>*/}
            </div>
            <div className="user-data__block">
                <Input label={'E-mail'} id={'email'} name={'email'} type={'email'} size={15} disabled={true}/>
                {/*<h4 className="input__label">E-mail</h4>*/}
                {/*<input id="email" type="email" name="email" className="input-field" size="15" disabled>*/}
            </div>
            <div className="user-data__block">
                <Input label={'Пароль'} id={'password'} name={'password'} type={'password'} size={15} disabled={true}/>
                {/*<h4 className="input__label">Пароль</h4>*/}
                {/*<input id="password" type="password" name="email" className="input-field" size="15" disabled>*/}
            </div>
        </div>
    );
}

export default UserData;