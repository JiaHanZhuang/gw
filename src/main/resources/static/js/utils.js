class Apply extends React.Component {
    constructor(){
        super();
        this.getToken();
        this.state={flag:true,name:"",sex:"1",profession:"",phone:"",
            qq:"",email:"",department:"Java部",resume:"",
            emailFlag:true,phoneFlag:true,title:"立即报名",token:""}
        this.appluByName=this.appluByName.bind(this);
        this.applyBySex=this.applyBySex.bind(this);
        this.applyByProfession=this.applyByProfession.bind(this);
        this.applyByPhone=this.applyByPhone.bind(this);
        this.applyByQQ=this.applyByQQ.bind(this);
        this.applyByEmail=this.applyByEmail.bind(this);
        this.applyByDepartment=this.applyByDepartment.bind(this);
        this.applyByResume=this.applyByResume.bind(this);
        this.apply=this.apply.bind(this);
        this.checkNull = this.checkNull.bind(this);
        this.getToken = this.getToken.bind(this);
    }

    getToken(){
        let that = this;
        $.get("/aode/getToken",function (result) {
            that.setState({
                token:result.token
            });
            console.log(result);
        })
    }

    appluByName(e){
            this.setState({
                name:e.target.value
            },function () {
                this.checkNull()
            });
    }

    applyBySex(e) {
        this.setState({
            sex:e.target.value
        },function () {
            this.checkNull()
        });
    }

    applyByProfession(e){
        this.setState({
            profession:e.target.value
        },function () {
            this.checkNull()
        });
    }

    applyByPhone(e){
        let reg = /^((13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])+\d{8})$/;
        let value = e.target.value;
        this.setState({
            phone:value
        },function () {
            if(reg.test(value)) {
                this.setState({
                    phoneFlag:true
                },this.checkNull());
            } else {
                this.setState({
                    phoneFlag:false
                },this.checkNull());
            }
        });
    }

    applyByQQ(e){
        this.setState({
            qq:e.target.value
        },function () {
            this.checkNull()
        });
    }

    applyByEmail(e){
        let value = e.target.value;
        let reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        this.setState({
            email:value
        },function () {
            if(reg.test(value)) {
                this.setState({
                    emailFlag:true
                },this.checkNull());
            }else{
                this.setState({
                    emailFlag:false
                },this.checkNull());
            }
        });
    }

    applyByDepartment(e){
        this.setState({
            department:e.target.value
        },function () {
            this.checkNull()
        });
    }

    applyByResume(e){
        this.setState({
            resume:e.target.value
        },function () {
            this.checkNull()
        });
    }

    checkNull(){
        let that = this.state;
        if(that.email !== ""  && that.qq !== ""
            && that.phone !== ""  && that.profession !== ""
            && that.name !== "") {
            this.setState({
               flag:false
            });
        } else {
            this.setState({
                flag:true
            });
        }
    }


    apply(){
        let that = this;
        if(that.state.emailFlag && that.state.phoneFlag) {
            that.setState({
                title:"提交中，请稍等...",
                flag:true
            });
            if(!that.state.flag){
                $.post("/aode/apply",{
                    name:that.state.name,
                    sex:that.state.sex,
                    profession:that.state.profession,
                    phone:that.state.phone,
                    qq:that.state.qq,
                    email:that.state.email,
                    department:that.state.department,
                    resume:that.state.resume,
                    token:that.state.token,
                },function (result) {
                    that.setState({
                        title:"立即报名",
                        flag:false
                    },function () {
                        that.getToken();
                    });
                    // console.log(result);
                    // alert(result.data);
                    alert(result.message);
                });
            }
        }
    }

    render(){
        // let flag = this.state.flag;
        return(
            <section className="banner-bottom-w3ls pt-lg-5 pt-md-3 pt-3">
                <div className="inner-sec-wthreelayouts pt-md-5 pt-md-3 pt-3">
                    <h2 className="heading-agileinfo text-center  mb-4">立即 报名</h2>
                    <div className="container-fluid">
                        <div className="row">
                            <div className="col-md-3 map"/>
                            <div className="col-md-6 main_grid_contact">
                                <div className="form">
                                    <h4 className="mb-4 text-left">填写报名信息</h4>
                                    <form action="#" method="post">
                                        <div className="form-group">
                                            <label className="my-2">姓名</label>
                                            <input className="form-control" type="text" name="Name" onChange={this.appluByName}/>
                                        </div>
                                        <div className="form-group">
                                            <label className="my-2">性别</label>
                                            <select className="form-control" name="sex" onChange={this.applyBySex}>
                                                <option value="1" style={{background:"#fff"}}>男</option>
                                                <option value="0" style={{background:"#fff"}}>女</option>
                                            </select>
                                        </div>
                                        <div className="form-group">
                                            <label className="my-2">专业</label>
                                            <input className="form-control" type="text" name="profession" onChange={this.applyByProfession}/>
                                        </div>
                                        <div className="form-group">
                                            <div>
                                                <label className="my-2">手机号码</label>
                                                <label className="my-4">&nbsp;&nbsp;&nbsp;</label>
                                                <label className="my-4" hidden={this.state.phoneFlag} style={{color:"red"}}>*手机号码格式不正确</label>
                                            </div>
                                            <input className="form-control" type="text" name="phone" onChange={this.applyByPhone}/>
                                        </div>
                                        <div className="form-group">
                                            <label className="my-2">QQ号码</label>
                                            <input className="form-control" type="text" name="qq" onChange={this.applyByQQ}/>
                                        </div>
                                        <div className="form-group">
                                            <div>
                                                <label className="my-2">邮箱</label>
                                                <label className="my-4">&nbsp;&nbsp;&nbsp;</label>
                                                <label className="my-4" hidden={this.state.emailFlag} style={{color:"red"}}>*邮箱格式不正确</label>
                                            </div>
                                            <input className="form-control" type="email" name="email" onChange={this.applyByEmail}/>
                                        </div>
                                        <div className="form-group">
                                            <label className="my-2">报名部门</label>
                                            <select className="form-control" name="department" onChange={this.applyByDepartment}>
                                                <option value="Java部" style={{background:"#fff"}}>Java部</option>
                                                <option value="美工部" style={{background:"#fff"}}>美工部</option>
                                                <option value=".Net部" style={{background:"#fff"}}>.Net部</option>
                                                <option value="行政部" style={{background:"#fff"}}>行政部</option>
                                                <option value="php部" style={{background:"#fff"}}>php部</option>
                                            </select>
                                        </div>
                                        <div className="form-group">
                                            <label>个人简历</label>
                                            <textarea id="textarea" name="resume" onChange={this.applyByResume}/>
                                        </div>
                                        <div className="input-group1" >
                                            {this.state.flag?(
                                                <input className="form-control" type="button" defaultValue={this.state.title}  disabled={this.state.flag}  style={{background: "#1b1e21"}}/>
                                            ):(
                                                <input className="form-control" type="button" defaultValue={this.state.title}  disabled={this.state.flag} onClick={this.apply}/>
                                            )}

                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div className="col-md-3 map"/>
                        </div>
                    </div>
                </div>
            </section>
        );
    }
}



class UserFrom extends React.Component {
    constructor(){
        super();
        this.state={textValue : '账户' , password:'密码'};
        this.changeUsername=this.changeUsername.bind(this);
        this.changePassword=this.changePassword.bind(this);
        this.sumbit = this.sumbit.bind(this);
    }
    changeUsername(e){
        this.setState({
            textValue:e.target.value
        });
    }
    changePassword(e){
        this.setState({
            password:e.target.value
        });
    }
    sumbit(){
        let username = this.state.textValue;
        let password = this.state.password;
        let boolean = true;
        if(username === '') {
            boolean = false;
            alert("账户不可为空")
        } else if(password === '') {
            boolean = false;
            alert("密码不可为空")
        } else if(password.length < 5 || password.length > 16) {
            boolean = false;
            alert("密码长度不得小于5字符，不得大于16字符")
        }
        if(boolean) {
            $.post("/aode/admin/loginAdmin",{
                username:username,
                password:password
            },function (result) {
                if(result.message == null) {
                    window.location.replace("/aode/admin/main");
                }else{
                    alert(result.message)
                }
            })
        }
    }
    render(){
        return(
            <ul>
                <li><input name="" type="text" className="loginuser" placeholder={this.state.textValue} onChange={this.changeUsername}/></li>
                <li><input name="" type="password" className="loginpwd" placeholder={this.state.password}  onChange={this.changePassword}/></li>
                <li><input name="" type="button" className="loginbtn" value="登录" onClick={this.sumbit}/></li>
            </ul>
        );
    }
}


class Message extends React.Component {
    constructor(){
        super();
        this.state = {admin:""}
        var that = this;
        $.get("/aode/admin/selectMessage",function (result) {
            that.setState({
                admin:result.obj
            });
        })
    }

    render(){
        return(<span>{this.state.admin.username}</span>);
    }
}

class SelectApply extends React.Component {
    constructor(){
        super();
        this.state = {name:"",phone:"",department:"Java部"}
        this.changeName=this.changeName.bind(this);
        this.changePhone=this.changePhone.bind(this);
        this.changeDepartment=this.changeDepartment.bind(this);
        this.sumbit=this.sumbit.bind(this);
    }

    changeName(e){
        this.setState({
            name:e.target.value
        });
    }

    changePhone(e){
        this.setState({
            phone:e.target.value
        });
    }

    changeDepartment(e){
        this.setState({
            department:e.target.value
        });
    }

    sumbit(){
        let name = this.state.name;
        let phone = this.state.phone;
        let department = this.state.department;
        let flag = true;
        if(name === ""){
            flag = false;
        }
        if(phone === ""){
            flag = false;
        }
        if(flag) {
            $.post("/aode/applyResult",{
                name:name,
                phone:phone,
                department:department
            },function (result) {
                alert(result.message)
            })
        }
    }

    render(){
        return(
            <div>
                    <div className="reg-fom-btm mt-5">
                        <div className="fields">
                            <span className="text-white mb-2">查询的姓名</span>
                            <input type="text" className="form-control" defaultValue={this.state.name} onChange={this.changeName}/>
                        </div>
                    </div>
                    <div className="reg-fom-btm mt-3">
                        <div className="fields">
                            <span className="text-white mb-2">查询的电话号码</span>
                            <input type="text" className="form-control" defaultValue={this.state.phone} onChange={this.changePhone}/>
                        </div>
                    </div>
                    <div className="reg-fom-btm mt-3">
                        <div className="fields">
                            <span className="text-white mb-2">查询部门</span>
                            <select className="form-control" onChange={this.changeDepartment}>
                                <option value="Java部">Java部</option>
                                <option value="美工部">美工部</option>
                                <option value=".Net部">.Net部</option>
                                <option value="行政部">行政部</option>
                                <option value="php部">php部</option>
                            </select>
                        </div>
                    </div>
                    <div className="reg-fom-btm mt-3">
                        <input type="submit" value="查询" onClick={this.sumbit}/>
                    </div>
            </div>
        );
    }
}


class LoginUp extends React.Component {
    constructor(){
        super();
        this.state = {username:"",password:"",authCode:""};
        this.authCode = this.authCode.bind(this);
        this.changeUsername = this.changeUsername.bind(this);
        this.changePassword = this.changePassword.bind(this);
        this.loginUp = this.loginUp.bind(this);
    }

    changeUsername(e){
        this.setState({
            username:e.target.value
        });
    }

    changePassword(e){
        this.setState({
            password:e.target.value
        });
    }

    authCode(e){
        this.setState({
            authCode:e.target.value
        });
    }

    loginUp(){
        let username = this.state.username;
        let password = this.state.password;
        let code = this.state.authCode;
        let boolean = true;
        if(username === "") {
            alert("用户名不能为空")
            boolean = false;
        } else if(password === "") {
            alert("密码不能为空")
            boolean = false;
        } else if(password.length < 5 && password.length > 16){
            alert("密码长度不得小于5字符，不得大于16字符")
            boolean = false;
        } else if(code === "") {
            alert("验证码不能为空")
            boolean = false;
        }
        if(boolean) {
            $.post("/aode/admin/loginUp",{
                username:username,
                password:password,
                code:code
            },function (result) {
                alert(result.message)
            })
        }
    }

    render(){
        return(
            <ul className="forminfo">
                <li><label>账号</label><input type="text" className="dfinput" onChange={this.changeUsername}/></li>
                <li><label>密码</label><input type="password" className="dfinput" onChange={this.changePassword}/><i>密码长度不得小于5字符，不得大于16字符</i></li>
                <li><label>验证码</label><input type="text" className="dfinput" onChange={this.authCode}/></li>
                <li><label>&nbsp;</label><input type="button" className="btn" defaultValue="确认注册" onClick={this.loginUp}/></li>
            </ul>
        );
    }
}

class ApplyMessage extends React.Component {
    constructor(){
        super();
        this.state={applyMessage:"",sexScale:""};
        this.applyMessageNumber = this.applyMessageNumber.bind(this);
        this.showSexScale = this.showSexScale.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
        this.applyMessageNumber()
    }

    applyMessageNumber(){
        const department = ["Java部","美工部","php部",".Net部","行政部"];
        let that = this;
        $.post("/aode/admin/findDepartmentNumber",{
            department:department
        },function (result) {
            that.setState({
                applyMessage:result.obj
            });
        })
    }

    showSexScale(e){
        let that = this;
        let department = e.target.getAttribute("data-value");
        $.post("/aode/admin/findDepartmentSex",{
            department:department,
            sex :[0,1]
        },function (result) {
            alert("女："+result.obj[0].number+" 男："+result.obj[1].number)
        });
    }

    closeBlock(){
        $(".tiptop a").click(function(){
            $(".tip").fadeOut(200);
        });
    }

    render(){
        const obj = this.state.applyMessage;
        return(
            <table className="imgtable">

                <thead>
                <tr>
                    <th>部门名称</th>
                    <th>报名人数</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                    {
                        Object.keys(obj).map(key =>{
                            return(
                                <tr>
                                    <td>{obj[key].department}</td>
                                    <td>{obj[key].number}</td>
                                    <td>
                                        <a href="#" >审查报名信息</a>&nbsp;
                                        <a href="#" onClick={this.showSexScale} data-value={obj[key].department}>查看部门男女比例</a>
                                    </td>
                                </tr>
                            )
                        })
                    }
                </tbody>

            </table>
        );
    }
}

class SexMessage extends React.Component {
    constructor(){
        super();
        this.state={woman:"",man:""};
        this.sexNumber = this.sexNumber.bind(this);
        this.closeBlock = this.closeBlock.bind(this);
        this.sexNumber();
    }

    sexNumber(){
        let that = this;
        $.post("/aode/admin/findSexNumber",{
            sex:[0,1]
        },function (result) {
            that.setState({
                woman:result.obj[0].number,
                man:result.obj[1].number
            });
        })
    }

    closeBlock(){
        $(".tiptop a").click(function(){
            $(".tip").fadeOut(200);
        });
    }

    render(){
        return(
            <div className="tip">
                <div className="tiptop"><span>提示信息</span><a onClick={this.closeBlock}></a></div>

                <div className="tipinfo">
                    <span><img src="/static/admin/images/ticon.png"/></span>
                    <div className="tipright">
                        <p>报名男女人数为：</p>
                        <cite>女：男 = {this.state.woman} : {this.state.man} </cite>
                    </div>
                </div>
            </div>
        );
    }
}