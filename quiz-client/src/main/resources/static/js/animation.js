var orbit = $('.orbit'),
    centerInner = $('.center-inner'),
    centerMiddle = $('.center-middle'),
    centerOuter = $('.center-outer'),
    tl;

TweenLite.set(centerInner, {x: 90, y: 90});
TweenLite.set(centerMiddle, {x: 90, y: 90});
TweenLite.set(centerOuter, {x: 90, y: 90});

function addNodes(nodes) {

    var space = 0,
        spaceNode = 0,
        nodeLoop = 0,
        orbitLoop = 0,
        orbitNames = Array('inner', 'middle', 'outer'),
        numberNodes = 0;
    //var projectNmae = ["irita","e commerce" , "govt project", "android" ,"web" ,"irita","e commerce" , "govt project", "android" ,"web" ,"ios"];
    var projectNmae ;
    $.ajax({
        type: "GET",
        url: "/projects/name",
        success: function (response) {
                projectNmae=response;
            alert(projectNmae)

        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
    //alert(nodes.length);
    for (orbitLoop; orbitLoop < nodes.length; orbitLoop++) {
        var index = 0;
        if (orbitLoop == 0) {
            index = 0;
        } else if (orbitLoop == 1) {
            index = nodes[0];
        } else {
            index = nodes[1] + nodes[0];
        }
        nodeLoop = 0;
        numberNodes = nodes[orbitLoop];
        space = 360 / numberNodes;
        //alert(numberNodes);

        for (nodeLoop; nodeLoop < numberNodes; nodeLoop++) {

            spaceNode = space + ( Math.random() * ( space / ( numberNodes * 2 ) ) );
            //spaceNode = space;

            var newPivot = $("<div>", {class: "pivot pivot-" + orbitNames[orbitLoop]}).appendTo($('.center-' + orbitNames[orbitLoop])),
                newNode = $("<a href='#' data-orbit='tl" + orbitNames[orbitLoop] + "' class='node node-" + orbitNames[orbitLoop] + "' id='project" + projectNmae[index + nodeLoop] + "' onclick='getProjectInfo()'>" + projectNmae[index + nodeLoop] + "</a>").appendTo(newPivot);


            TweenLite.set(newPivot, {
                rotation: nodeLoop * spaceNode,
                transformOrigin: "0px " + (orbitLoop * 50 + 150 + 'px')
            });
            TweenLite.set(newNode, {rotation: -nodeLoop * spaceNode, transformOrigin: "50% 50%"});
        }
    }
}

// Build nodes
addNodes(Array(2, 5, 4));
// Fade in
TweenLite.from(orbit, 1, {autoAlpha: 0});
// Animation setup
tlinner = new TimelineMax({repeat: -1});
tlmiddle = new TimelineMax({repeat: -1});
tlouter = new TimelineMax({repeat: -1});
// Animate centers
tlinner.to(centerInner, 30, {rotation: 360, ease: Linear.easeNone});
tlmiddle.to(centerMiddle, 40, {rotation: -360, ease: Linear.easeNone});
tlouter.to(centerOuter, 50, {rotation: 360, ease: Linear.easeNone});
// Counter-animate nodes to keep text level
tlinner.to($(".node-inner"), 30, {rotation: "-=360", ease: Linear.easeNone}, 0);
tlmiddle.to($(".node-middle"), 40, {rotation: "+=360", ease: Linear.easeNone}, 0);
tlouter.to($(".node-outer"), 50, {rotation: "-=360", ease: Linear.easeNone}, 0);


// Interaction
$('.node').mouseenter(function () {
    $(this).addClass('active');
    var orbit = $(this).data(orbit);
    TweenLite.to($(this), 0.75, {backgroundColor: "rgba(255,255,255,0.9)", scale: 1.25, ease: Elastic.easeOut});
    window[orbit.orbit].pause();
});
$('.node').mouseleave(function () {
    $(this).removeClass('active');
    var orbit = $(this).data(orbit);
    TweenLite.to($(this), 2, {backgroundColor: "rgba(255,255,255,0.4)", scale: 1, ease: Elastic.easeOut});
    window[orbit.orbit].resume();
});

function getProjectInfo() {
    $(".text-hi").show();
}