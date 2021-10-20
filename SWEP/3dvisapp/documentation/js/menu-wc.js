'use strict';


customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">three-d-vis-app documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-toggle="collapse" ${ isNormalMode ?
                                'data-target="#modules-links"' : 'data-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link">AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-4253f8c259519a82de1fb6c7f5b795d6"' : 'data-target="#xs-components-links-module-AppModule-4253f8c259519a82de1fb6c7f5b795d6"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-4253f8c259519a82de1fb6c7f5b795d6"' :
                                            'id="xs-components-links-module-AppModule-4253f8c259519a82de1fb6c7f5b795d6"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/DetailComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">DetailComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HoverComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">HoverComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MenuComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">MenuComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/Visualizer3dComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">Visualizer3dComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/MaterialModule.html" data-type="entity-link">MaterialModule</a>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#classes-links"' :
                            'data-target="#xs-classes-links"' }>
                            <span class="icon ion-ios-paper"></span>
                            <span>Classes</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"' }>
                            <li class="link">
                                <a href="classes/ApiGeoData.html" data-type="entity-link">ApiGeoData</a>
                            </li>
                            <li class="link">
                                <a href="classes/ApiObject.html" data-type="entity-link">ApiObject</a>
                            </li>
                            <li class="link">
                                <a href="classes/ApiResponse.html" data-type="entity-link">ApiResponse</a>
                            </li>
                            <li class="link">
                                <a href="classes/ConfigData.html" data-type="entity-link">ConfigData</a>
                            </li>
                            <li class="link">
                                <a href="classes/Coordinate.html" data-type="entity-link">Coordinate</a>
                            </li>
                            <li class="link">
                                <a href="classes/Feature.html" data-type="entity-link">Feature</a>
                            </li>
                            <li class="link">
                                <a href="classes/FeatureCollection.html" data-type="entity-link">FeatureCollection</a>
                            </li>
                            <li class="link">
                                <a href="classes/GeoData.html" data-type="entity-link">GeoData</a>
                            </li>
                            <li class="link">
                                <a href="classes/Geometry.html" data-type="entity-link">Geometry</a>
                            </li>
                            <li class="link">
                                <a href="classes/MapOverlay.html" data-type="entity-link">MapOverlay</a>
                            </li>
                            <li class="link">
                                <a href="classes/MetaListing.html" data-type="entity-link">MetaListing</a>
                            </li>
                            <li class="link">
                                <a href="classes/MetaSingle.html" data-type="entity-link">MetaSingle</a>
                            </li>
                            <li class="link">
                                <a href="classes/MultiPolygon.html" data-type="entity-link">MultiPolygon</a>
                            </li>
                            <li class="link">
                                <a href="classes/Polygon.html" data-type="entity-link">Polygon</a>
                            </li>
                            <li class="link">
                                <a href="classes/Satellite.html" data-type="entity-link">Satellite</a>
                            </li>
                            <li class="link">
                                <a href="classes/Terminal.html" data-type="entity-link">Terminal</a>
                            </li>
                            <li class="link">
                                <a href="classes/TerminalLiveData.html" data-type="entity-link">TerminalLiveData</a>
                            </li>
                            <li class="link">
                                <a href="classes/Uid.html" data-type="entity-link">Uid</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#injectables-links"' :
                                'data-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/ApiService.html" data-type="entity-link">ApiService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/BasicObjectService.html" data-type="entity-link">BasicObjectService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/ConfigurationService.html" data-type="entity-link">ConfigurationService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/ControlService.html" data-type="entity-link">ControlService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/DataService.html" data-type="entity-link">DataService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LightService.html" data-type="entity-link">LightService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/MapdataService.html" data-type="entity-link">MapdataService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/MeshControllerService.html" data-type="entity-link">MeshControllerService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/ObjectGeneratorService.html" data-type="entity-link">ObjectGeneratorService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/ObjectPlacerService.html" data-type="entity-link">ObjectPlacerService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/SceneService.html" data-type="entity-link">SceneService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/TextureLoaderService.html" data-type="entity-link">TextureLoaderService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UiService.html" data-type="entity-link">UiService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#interfaces-links"' :
                            'data-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/GeoDataProvider.html" data-type="entity-link">GeoDataProvider</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/HasUid.html" data-type="entity-link">HasUid</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#miscellaneous-links"'
                            : 'data-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/enumerations.html" data-type="entity-link">Enums</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/functions.html" data-type="entity-link">Functions</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});