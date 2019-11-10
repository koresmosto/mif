<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/aux/common/taglibs.jsp" %>
<spring:message code="userManagement" var="userManagementVar"/>

<t:concretpage title="${userManagementVar}">
    <title>AngularJS $http Example</title>
    <style>
        .username.ng-valid {
            background-color: lightgreen;
        }

        .username.ng-dirty.ng-invalid-required {
            background-color: red;
        }

        .username.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }

        .email.ng-valid {
            background-color: lightgreen;
        }

        .email.ng-dirty.ng-invalid-required {
            background-color: red;
        }

        .email.ng-dirty.ng-invalid-email {
            background-color: yellow;
        }

        input.form-control, select.form-control {
            width: 62ch;
        }
    </style>
    <link href="/public/app-user.css" rel="stylesheet"/>
    </head>
    <body ng-app="myApp" class="ng-cloak">
    <div class="generic-container" ng-controller="UserController as ctrl">
        <div class="panel panel-default">
            <div class="panel-heading"><span class="lead">User Registration Form </span></div>
            <div class="formcontainer">
                <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.user.id"/>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="ssoid">SsoId</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.user.ssoId" name="ssoid" id="ssoid"
                                       ng-focus="isFocusOnSsoIdInput=true" ng-blur="isFocusOnSsoIdInput=false"
                                       class="username form-control input-sm"
                                       placeholder="Enter your name" required
                                       ng-minlength="3"/>
                                <div class="has-error" ng-show="myForm.ssoid.$dirty">
                                    <span ng-show="myForm.ssoid.$error.required  && !isFocusOnSsoIdInput">
                                        This is a required field
                                    </span>
                                    <span ng-show="myForm.ssoid.$error.minlength  && !isFocusOnSsoIdInput">
                                        Minimum length required is 3
                                    </span>
                                    <span ng-show="myForm.ssoid.$invalid  && !isFocusOnSsoIdInput">
                                        This field is invalid
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="password">Password</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.user.password" name="password" id="password"
                                       ng-focus="isFocusOnPasswordInput=true" ng-blur="isFocusOnPasswordInput=false"
                                       class="username form-control input-sm" placeholder="Enter your password"
                                       title="- Entering password is in normal form&#xA;
                                       - Showing password is in BCrypt encoded form"
                                       required
                                       ng-minlength="5"/>
                                <div class="has-error" ng-show="myForm.password.$dirty">
                                    <span ng-show="myForm.password.$error.required && !isFocusOnPasswordInput">
                                        This is a required field
                                    </span>
                                    <span ng-show="myForm.password.$error.minlength && !isFocusOnPasswordInput">
                                        Minimum length required is 5
                                    </span>
                                    <span ng-show="myForm.password.$invalid && !isFocusOnPasswordInput">
                                        This field is invalid
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="email">Email</label>
                            <div class="col-md-7">
                                <input type="email" ng-model="ctrl.user.email" name="email" id="email"
                                       ng-focus="isFocusOnEmailInput=true" ng-blur="isFocusOnEmailInput=false"
                                       class="email form-control input-sm" placeholder="Enter your Email" required/>
                                <div class="has-error" ng-show="myForm.email.$dirty">
                                    <span ng-show="myForm.email.$error.required && !isFocusOnEmailInput">
                                        This is a required field
                                    </span>
                                    <span ng-show="myForm.email.$invalid && !isFocusOnEmailInput">
                                        This field is invalid
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="state">State</label>
                            <div class="col-md-7">
                                <select ng-model="ctrl.user.state" class="username form-control input-sm" id="state"
                                        ng-focus="isFocusOnStateInput=true" ng-blur="isFocusOnStateInput=false"
                                        placeholder="Enter your Address. [This field is validation free]" required>
                                    <option ng-selected="ctrl.user.state == key"
                                            ng-repeat="(key, value) in userStatesMap"
                                            ng-value="key">
                                        {{value}}
                                    </option>
                                </select>
                                <div class="has-error" ng-show="myForm.state.$dirty">
                                    <span ng-show="myForm.state.$error.required && !isFocusOnStateInput">
                                        This is a required field
                                    </span>
                                    <span ng-show="myForm.state.$invalid && !isFocusOnStateInput">
                                        This field is invalid
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-actions floatRight">
                            <input type="submit" value="{{!ctrl.user.id ? 'Add' : 'Update'}}"
                                   class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                            <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm">
                                Reset Form
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Users </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Id.</th>
                        <th>SsoId</th>
                        <th>Password</th>
                        <th>Email</th>
                        <th>State</th>
                        <th width="20%"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="u in ctrl.users">
                        <td><span ng-bind="u.id"></span></td>
                        <td><span ng-bind="u.ssoId"></span></td>
                        <td>
                            <div>
                                <span title="{{u.password}}"
                                      ng-bind="'...' + u.password.substring(u.password.length-7,u.password.length)">
                                </span>
                                <span><button class="btn" data-clipboard-text="{{u.password}}">Copy</button></span>
                            </div>
                        </td>
                        <td><span ng-bind="u.email"></span></td>
                        <td><span ng-bind="u.state"></span></td>
                        <td>
                            <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">
                                Edit
                            </button>
                            <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">
                                Remove
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="/public/js/lib/import/angular.js"></script>
    <script src="/public/js/lib/clipboard_aux.js"></script>
    <script src="/public/js/app.js"></script>
    <script src="/public/js/service/user_service.js"></script>
    <script src="/public/js/controller/user_controller.js"></script>
</t:concretpage>