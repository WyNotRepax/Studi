$wnd.showcase.runAsyncCallback24("function Jrb(a){this.a=a}\nfunction Lrb(a){this.a=a}\nfunction Nrb(a){this.a=a}\nfunction Srb(a,b){this.a=a;this.b=b}\nfunction CVb(a){return eKb(),a.hb}\nfunction GVb(a,b){zVb(a,b);jp((eKb(),a.hb),b)}\nfunction XJb(){var a;if(!UJb||$Jb()){a=new _gc;ZJb(a);UJb=a}return UJb}\nfunction $Jb(){var a=$doc.cookie;if(a!=VJb){VJb=a;return true}else{return false}}\nfunction jp(b,c){try{b.remove(c)}catch(a){b.removeChild(b.childNodes[c])}}\nfunction _Jb(a){WJb&&(a=encodeURIComponent(a));$doc.cookie=a+'=;expires=Fri, 02-Jan-1970 00:00:00 GMT'}\nfunction Grb(a){var b,c,d,e;if(CVb(a.c).options.length<1){LXb(a.a,'');LXb(a.b,'');return}e=CVb(a.c).selectedIndex;b=DVb(a.c,e);c=(d=XJb(),HP(b==null?dcc(rhc(d.d,null)):Jhc(d.e,b)));LXb(a.a,b);LXb(a.b,c)}\nfunction Frb(a,b){var c,d,e,f,g,h,i;fh(a.c).options.length=0;h=0;e=new sdc(XJb());for(d=(g=e.a.Yh().fc(),new xdc(g));d.a.Sg();){c=(f=DP(d.a.Tg(),36),HP(f.ci()));yVb(a.c,c);I9b(c,b)&&(h=fh(a.c).options.length-1)}i=h;ym((rm(),qm),new Srb(a,i))}\nfunction ZJb(b){var c=$doc.cookie;if(c&&c!=''){var d=c.split('; ');for(var e=d.length-1;e>=0;--e){var f,g;var h=d[e].indexOf('=');if(h==-1){f=d[e];g=''}else{f=d[e].substring(0,h);g=d[e].substring(h+1)}if(WJb){try{f=decodeURIComponent(f)}catch(a){}try{g=decodeURIComponent(g)}catch(a){}}b.$h(f,g)}}}\nfunction Erb(a){var b,c,d;c=new HTb(3,3);a.c=new IVb;b=new pNb('Supprimer');Eh((eKb(),b.hb),jrc,true);aTb(c,0,0,'<b><b>Cookies existants:<\\/b><\\/b>');dTb(c,0,1,a.c);dTb(c,0,2,b);a.a=new UXb;aTb(c,1,0,'<b><b>Nom:<\\/b><\\/b>');dTb(c,1,1,a.a);a.b=new UXb;d=new pNb('Sauvegarder Cookie');Eh(d.hb,jrc,true);aTb(c,2,0,'<b><b>Valeur:<\\/b><\\/b>');dTb(c,2,1,a.b);dTb(c,2,2,d);Lh(d,new Jrb(a),(Qt(),Qt(),Pt));Lh(a.c,new Lrb(a),(Ht(),Ht(),Gt));Lh(b,new Nrb(a),(null,Pt));Frb(a,null);return c}\nK9(475,1,Znc,Jrb);_.Wc=function Krb(a){var b,c,d;c=HXb(this.a.a);d=HXb(this.a.b);b=new tO(g9(m9((new rO).q.getTime()),rsc));if(c.length<1){$wnd.alert('Vous devez indiquer un nom de cookie');return}aKb(c,d,b);Frb(this.a,c)};var K$=R8b(loc,'CwCookies/1',475);K9(476,1,$nc,Lrb);_.Vc=function Mrb(a){Grb(this.a)};var L$=R8b(loc,'CwCookies/2',476);K9(477,1,Znc,Nrb);_.Wc=function Orb(a){var b,c;c=fh(this.a.c).selectedIndex;if(c>-1&&c<fh(this.a.c).options.length){b=DVb(this.a.c,c);_Jb(b);GVb(this.a.c,c);Grb(this.a)}};var M$=R8b(loc,'CwCookies/3',477);K9(478,1,goc);_.Ec=function Rrb(){$bb(this.b,Erb(this.a))};K9(479,1,{},Srb);_.Gc=function Trb(){this.b<fh(this.a.c).options.length&&HVb(this.a.c,this.b);Grb(this.a)};_.b=0;var O$=R8b(loc,'CwCookies/5',479);var UJb=null,VJb;alc(Fl)(24);\n//# sourceURL=showcase-24.js\n")