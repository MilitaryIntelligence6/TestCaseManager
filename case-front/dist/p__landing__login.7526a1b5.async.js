(window.webpackJsonp=window.webpackJsonp||[]).push([[8],{Aqrb:function(e,t,a){"use strict";a.r(t);a("+L6B");var n=a("2/Rp"),s=(a("y8nQ"),a("Vl3Y")),r=(a("5NDa"),a("5rEg")),i=(a("Pwec"),a("CtXQ")),l=(a("miYZ"),a("tsqr")),o=a("p0pE"),c=a.n(o),p=a("q1tI"),d=a.n(p),m=(a("nXRE"),a("on7z")),u={getQueryString(e){var t=new RegExp("(^|&)"+e+"=([^&]*)(&|$)"),a=window.location.search.substr(1).match(t);return null!=a?unescape(a[2]):null},setcookie(e,t){var a=new Date;a.setTime(a.getTime()+2592e6),document.cookie=e+"="+escape(t)+";expires="+a.toGMTString()}};t.default=s.a.create()(class extends d.a.PureComponent{constructor(e){super(e),this.typeChange=(e=>{this.setState({type:e},()=>{this.props.form.resetFields()})}),this.onOk=(()=>{this.props.form.validateFields((e,t)=>{e||(this.setState({loading:!0}),"1"===this.state.type?Object(m.a)("/user/login",{method:"POST",body:c()({},t)}).then(e=>{e&&200===e.code?(l.a.success("登陆成功"),window.location.href=u.getQueryString("jumpto")):l.a.error(e.msg),this.setState({loading:!1})}):Object(m.a)("/user/register",{method:"POST",body:c()({},t)}).then(e=>{e&&200===e.code?(l.a.success("注册成功"),window.location.href=window.location.href.split("?")[1]):l.a.error(e.msg),this.setState({loading:!1})}))})}),this.state={type:"1",loading:!1}}render(){var e=this.props.form.getFieldDecorator,t=this.state,a=t.type,l=t.loading;return d.a.createElement("div",{className:"login"},d.a.createElement("div",{className:"card"},d.a.createElement("div",{className:"title"},"JFUnitTestCaseManager",d.a.createElement("span",null,"一套敏捷的测试用例管理平台")),d.a.createElement("span",{className:"1"===a?"btn btn_active":"btn",onClick:()=>this.typeChange("1")},"登录"),d.a.createElement("span",{className:"2"===a?"btn btn_active":"btn",onClick:()=>this.typeChange("2")},"注册"),d.a.createElement("div",{className:"input"},d.a.createElement(s.a.Item,{label:""},e("username",{rules:[{required:!0,message:"请填写账号"}],initialValue:void 0})(d.a.createElement(r.a,{placeholder:"账号",prefix:d.a.createElement(i.a,{type:"user"})}))),"1"===a&&d.a.createElement(s.a.Item,{label:""},e("password",{rules:[{required:!0,message:"请填写密码"}],initialValue:void 0})(d.a.createElement(r.a.Password,{placeholder:"密码",prefix:d.a.createElement(i.a,{type:"lock"})}))),"2"===a&&d.a.createElement(s.a.Item,{label:""},e("password",{rules:[{required:!0,message:"请填写密码"}],initialValue:void 0})(d.a.createElement(r.a.Password,{placeholder:"密码",prefix:d.a.createElement(i.a,{type:"lock"})})))),d.a.createElement(n.a,{type:"primary",className:"onBtn",loading:l,onClick:()=>this.onOk()},"1"===a?"登录":"注册并登录")))}})},nXRE:function(e,t,a){e.exports={login:"login",card:"card",title:"title",btn:"btn",btn_active:"btn_active",input:"input",onBtn:"onBtn",zIndex:"zIndex"}}}]);