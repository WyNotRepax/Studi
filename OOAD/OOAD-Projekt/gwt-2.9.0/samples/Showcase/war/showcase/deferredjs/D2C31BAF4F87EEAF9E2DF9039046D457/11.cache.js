$wnd.showcase.runAsyncCallback11("function MSb(){}\nfunction OSb(){}\nfunction HSb(a,b){a.b=b}\nfunction ISb(a){if(a==xSb){return true}_A();return a==ASb}\nfunction JSb(a){if(a==wSb){return true}_A();return a==vSb}\nfunction NSb(a){this.b=(pUb(),kUb).a;this.e=(uUb(),tUb).a;this.a=a}\nfunction FSb(a,b){var c;c=FP(a.fb,161);c.b=b.a;!!c.d&&zNb(c.d,b)}\nfunction GSb(a,b){var c;c=FP(a.fb,161);c.e=b.a;!!c.d&&BNb(c.d,b)}\nfunction BSb(){BSb=O9;uSb=new MSb;xSb=new MSb;wSb=new MSb;vSb=new MSb;ySb=new MSb;zSb=new MSb;ASb=new MSb}\nfunction KSb(){BSb();DNb.call(this);this.b=(pUb(),kUb);this.c=(uUb(),tUb);(lKb(),this.e)[Qpc]=0;this.e[Rpc]=0}\nfunction CSb(a,b,c){var d;if(c==uSb){if(b==a.a){return}else if(a.a){throw i9(new A9b('Only one CENTER widget may be added'))}}Sh(b);C2b(a.j,b);c==uSb&&(a.a=b);d=new NSb(c);b.fb=d;FSb(b,a.b);GSb(b,a.c);ESb(a);Uh(b,a)}\nfunction DSb(a){var b,c,d,e,f,g,h;j2b((lKb(),a.hb),'',rrc);g=new ohc;h=new M2b(a.j);while(h.b<h.c.c){b=K2b(h);f=FP(b.fb,161).a;d=FP(scc(Ghc(g.d,f)),84);c=!d?1:d.a;e=f==ySb?'north'+c:f==zSb?'south'+c:f==ASb?'west'+c:f==vSb?'east'+c:f==xSb?'linestart'+c:f==wSb?'lineend'+c:xoc;j2b(Wo(b.hb),rrc,e);Ecc(g,f,N9b(c+1))}}\nfunction ESb(a){var b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;b=(lKb(),a.d);while(RLb(b)>0){Co(b,QLb(b,0))}o=1;e=1;for(i=new M2b(a.j);i.b<i.c.c;){d=K2b(i);f=FP(d.fb,161).a;f==ySb||f==zSb?++o:(f==vSb||f==ASb||f==xSb||f==wSb)&&++e}p=OO(q3,Klc,272,o,0,1);for(g=0;g<o;++g){p[g]=new OSb;p[g].b=$doc.createElement(Opc);yo(b,sKb(p[g].b))}k=0;l=e-1;m=0;q=o-1;c=null;for(h=new M2b(a.j);h.b<h.c.c;){d=K2b(h);j=FP(d.fb,161);r=$doc.createElement(Ppc);j.d=r;j.d[Dpc]=j.b;j.d.style[Epc]=j.e;j.d[amc]=j.f;j.d[_lc]=j.c;if(j.a==ySb){oKb(p[m].b,r,p[m].a);yo(r,sKb(d.hb));r[Bqc]=l-k+1;++m}else if(j.a==zSb){oKb(p[q].b,r,p[q].a);yo(r,sKb(d.hb));r[Bqc]=l-k+1;--q}else if(j.a==uSb){c=r}else if(ISb(j.a)){n=p[m];oKb(n.b,r,n.a++);yo(r,sKb(d.hb));r[trc]=q-m+1;++k}else if(JSb(j.a)){n=p[m];oKb(n.b,r,n.a);yo(r,sKb(d.hb));r[trc]=q-m+1;--l}}if(a.a){n=p[m];oKb(n.b,c,n.a);yo(c,sKb(fh(a.a)))}}\nvar rrc='cwDockPanel';N9(434,1,woc);_.Ec=function Fsb(){var a,b,c;fcb(this.a,(a=new KSb,(lKb(),a.hb).className='cw-DockPanel',a.e[Qpc]=4,HSb(a,(pUb(),jUb)),CSb(a,new hRb(lrc),(BSb(),ySb)),CSb(a,new hRb(mrc),zSb),CSb(a,new hRb(nrc),vSb),CSb(a,new hRb(orc),ASb),CSb(a,new hRb(prc),ySb),CSb(a,new hRb(qrc),zSb),b=new hRb(\"Voici un <code>panneau de d\\xE9filement<\\/code> situ\\xE9 au centre d'un <code>panneau d'ancrage<\\/code>. Si des contenus relativement volumineux sont ins\\xE9r\\xE9s au milieu de ce panneau \\xE0 d\\xE9filement et si sa taille est d\\xE9finie, il prend la forme d'une zone dot\\xE9e d'une fonction de d\\xE9filement \\xE0 l'int\\xE9rieur de la page, sans l'utilisation d'un IFRAME.<br><br>Voici un texte encore plus obscur qui va surtout servir \\xE0 faire d\\xE9filer cet \\xE9l\\xE9ment jusqu'en bas de sa zone visible. Sinon, il vous faudra r\\xE9duire ce panneau \\xE0 une taille minuscule pour pouvoir afficher ces formidables barres de d\\xE9filement!\"),c=new COb(b),c.hb.style[amc]='400px',c.hb.style[_lc]='100px',CSb(a,c,uSb),DSb(a),a))};N9(892,264,fmc,KSb);_.gc=function LSb(a){var b;b=xMb(this,a);if(b){a==this.a&&(this.a=null);ESb(this)}return b};var uSb,vSb,wSb,xSb,ySb,zSb,ASb;var r3=e9b(dmc,'DockPanel',892);N9(160,1,{},MSb);var o3=e9b(dmc,'DockPanel/DockLayoutConstant',160);N9(161,1,{161:1},NSb);_.c='';_.f='';var p3=e9b(dmc,'DockPanel/LayoutData',161);N9(272,1,{272:1},OSb);_.a=0;var q3=e9b(dmc,'DockPanel/TmpRow',272);plc(Fl)(11);\n//# sourceURL=showcase-11.js\n")
