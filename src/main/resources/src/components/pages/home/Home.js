import logo from "../../../assets/img/logo.png"
import pupils from "../../../assets/img/pupils.png"
// import Avatar from "./../img/ava.jpg"

import RegisterForm from "./RegisterForm"
import LoginForm from "./LoginForm"


const Home = () => {
    return ( 

        <div className="auth-grid">
            <img src={logo} className="welcome__logo" alt="logo"/>
  
            <div className="welcome__text">
                <p className="text-accented">Добро пожаловать</p>
                <p className="text">в платёжную систему</p>
                <p className="text text-small-accented">деловой игры КАРЬЕРА!</p>
            </div>
  
            <img src= {pupils} className="welcome__img" alt="pupils"/>
  
            <div className="auth__form">
  
                <div className="auth__form__tabs">
                    <div className="auth__form__tabs__tab">
  
                        <input type="radio" id="tab-login" name="auth-tabs" defaultChecked />
                        <label htmlFor="tab-login" className="auth__form__tabs__tab__title auth__tab--margin-reset">Вход</label>
  
                        <section className="auth__form__tabs__content">
                            {/* <form action="src/main/resources/src/components/pages/home/Home" id="login" className="form form__login"> */}
  
                                <div className="form-box">

                                    <LoginForm/>
                                    {/* <label className="form-box__label">E-mail</label>
                                    <input className="form-box__input" name="email"  type="text" />
                                    <label className="form-box__label">Пароль</label>
                                    <input className="form-box__input" name="password" type="password" /> */}
                                    {/* <a href="#" className="form-box--link">Не помню пароль</a> */}
                                </div>
  
                                {/* <button className="btn-form" type="submit">Войти</button> */}
  
                            {/* </form> */}
                        </section>
  
                        <div className="auth__form__tabs__tab">
  
                            <input type="radio" id="tab-registry" name="auth-tabs" />
                            <label htmlFor="tab-registry" className="auth__form__tabs__tab__title">Регистрация</label>
  
                            <section className="auth__form__tabs__content">
                                <div className="form-box">
                               
                                        <RegisterForm/>

                                        {/*
                                    <form method="post" id="register" className="form form__register">
                                         <label for="file" class="form-box__label-img"> <span style="background-color: gray;">+</span></label> */}
                                      
                                        {/* <label for="file" class="form-box__label-img"></label>
                                        <input type="file" id="file"/>

                                        <label className="form-box__label">Логин</label>
                                        <input className="form-box__input" type="text" name="login" />
  
                                        <label className="form-box__label">E-mail</label>
                                        <input className="form-box__input" type="text" />
  
                                        <label className="form-box__label">Пароль</label>
                                        <input className="form-box__input" type="password" name="password" />
  
                                        <label className="form-box__label">Подтверждение пароля</label>
                                        <input className="form-box__input" type="password" name="passwordConf" />
  
                                        <button className="form__button">Зарегистрироваться</button> 
                                     </form> */}

                                </div>
                        </section>
                </div>
            </div>
        </div>
    </div>
</div>


     );
}
 
export default Home;