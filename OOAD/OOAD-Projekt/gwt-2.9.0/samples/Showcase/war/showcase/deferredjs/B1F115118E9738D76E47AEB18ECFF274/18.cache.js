$wnd.showcase.runAsyncCallback18("function tA(){}\nfunction $Jb(a,b){Sx(a.a,b)}\nfunction tgb(a,b){this.b=a;this.a=b}\nfunction vgb(a,b){this.b=a;this.a=b}\nfunction TA(a){return RY(EA,a)}\nfunction sA(){sA=bY;rA=new tA}\nfunction ngb(a,b){jDb(b,'Selected: '+a.Nf()+', '+a.Of())}\nfunction kKb(){gKb();jKb.call(this,So($doc,'password'),'gwt-PasswordTextBox')}\nfunction QRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;return c.text.length}catch(a){return 0}}\nfunction PRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;return -c.move(Cdc,-65535)}catch(a){return 0}}\nfunction lgb(a,b){var c,d;c=new cHb;c.e[Tbc]=4;_Gb(c,a);if(b){d=new nDb('Selected: 0, 0');Ih(a,new tgb(a,d),(Au(),Au(),zu));Ih(a,new vgb(a,d),(Zt(),Zt(),Yt));_Gb(c,d)}return c}\nfunction SRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;var d=c.text.length;var e=0;var f=c.duplicate();f.moveEnd(Cdc,-1);var g=f.text.length;while(g==d&&f.parentElement()==b&&c.compareEndPoints('StartToEnd',f)<=0){e+=2;f.moveEnd(Cdc,-1);g=f.text.length}return d+e}catch(a){return 0}}\nfunction RRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;var d=c.duplicate();d.moveToElementText(b);d.setEndPoint('EndToStart',c);var e=d.text.length;var f=0;var g=d.duplicate();g.moveEnd(Cdc,-1);var h=g.text.length;while(h==e&&g.parentElement()==b){f+=2;g.moveEnd(Cdc,-1);h=g.text.length}return e+f}catch(a){return 0}}\nfunction mgb(){var a,b,c,d,e,f;f=new IQb;f.e[Tbc]=5;d=new iKb;rQb(d.hb,'','cwBasicText-textbox');$Jb(d,(sA(),sA(),rA));b=new iKb;rQb(b.hb,'','cwBasicText-textbox-disabled');b.hb[Lcc]='read only';Rx(b.a);b.hb[Kac]=true;FQb(f,new sDb('<b>Normal text box:<\\/b>'));FQb(f,lgb(d,true));FQb(f,lgb(b,false));c=new kKb;rQb(c.hb,'','cwBasicText-password');a=new kKb;rQb(a.hb,'','cwBasicText-password-disabled');a.hb[Lcc]='read only';Rx(a.a);a.hb[Kac]=true;FQb(f,new sDb('<br><br><b>Password text box:<\\/b>'));FQb(f,lgb(c,true));FQb(f,lgb(a,false));e=new MOb;rQb(e.hb,'','cwBasicText-textarea');e.hb.rows=5;FQb(f,new sDb('<br><br><b>Text area:<\\/b>'));FQb(f,lgb(e,true));return f}\nvar Cdc='character';aY(876,1151,{},tA);_.hd=function uA(a){return TA((NA(),a))?(Az(),zz):(Az(),yz)};var rA;var sI=oXb(k9b,'AnyRtlDirectionEstimator',876);aY(444,1,xac);_.Ec=function sgb(){u$(this.a,mgb())};aY(445,1,Bdc,tgb);_.Yc=function ugb(a){ngb(this.b,this.a)};var QN=oXb(Hac,'CwBasicText/2',445);aY(446,1,oac,vgb);_.Wc=function wgb(a){ngb(this.b,this.a)};var RN=oXb(Hac,'CwBasicText/3',446);aY(754,247,p8b);_.Nf=function bKb(){return PRb(this.hb)};_.Of=function cKb(){return QRb(this.hb)};aY(326,50,p8b,kKb);var JS=oXb(n8b,'PasswordTextBox',326);aY(216,312,p8b);_.Nf=function NOb(){return RRb(this.hb)};_.Of=function OOb(){return SRb(this.hb)};z7b(Cl)(18);\n//# sourceURL=showcase-18.js\n")
