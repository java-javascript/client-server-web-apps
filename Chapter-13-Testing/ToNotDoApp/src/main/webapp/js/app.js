angular.module('MyApp', ['ngResource']);

// Make a sortable table like http://jsfiddle.net/casimirs/ugngL/
function AppCtrl($scope, $resource, $http) {

    $scope.title = "To NOT Do"

    $scope.list = $resource('/api/tonotdo').query();

    $scope.doAddUpdate = function (externalKey, description, priority) {

        //
        // Bit of jQuery - in larger scale apps this would be enclosed in a directive
        // or use http://angular-ui.github.io/bootstrap/
        //
        $('#message').hide();
        if ($.trim(externalKey) == "" || $.trim(description) == "" || $.trim(priority) == "") {
            $('#messageText').text("All fields must be populated.")
            $('#message').show();
            return;
        }

        var o = {};
        o.description = description;
        o.priority = Number(priority);
        o.externalKey = externalKey;
        o.lastUpdated = moment().format('MM/DD/YYYY hh:mm:ss');

        console.log(JSON.stringify(o));

        $http.put('/api/tonotdo', o).success(function () {
            $scope.list = $resource('/api/tonotdo').query();
            $scope.description = "";
            $scope.priority = "";
            $scope.externalKey = "";
        });

    };

    $scope.doDelete = function (externalKey) {
        $resource('/api/tonotdo/' + externalKey, {method: 'DELETE'}).delete();
        $scope.list = $resource('/api/tonotdo').query();
    };

    $(document).on("click", '#message .close', function (e) {
        $('#message').hide();
    });

}

