<!DOCTYPE html>
<body>
<div id='wrap'>
    <header id='navbar_wrap'>
        <div class='no-select' id='navbar'>
            <a class='navbar__logo' href='/'></a>
            <ul id='nav_menu'>
                <li class='hide-6'>
                    <div class='badge'>
                        &nbsp;
                    </div>
                    <a href='/news'>News</a>
                </li>
                <li class='hide-5'>
                    <a href='/trending/tools'>Trending</a>
                </li>
                <li class='hide-4'>
                    <a href='/stacks'>Stacks</a>
                </li>
                <li class='hide-3'>
                    <a href='/categories'>Tools</a>
                    <div class='navmenu'>
                        <a href='/application_and_data'>Applications and Data</a>
                        <a href='/utilities'>Utilities</a>
                        <a href='/devops'>DevOps</a>
                        <a href='/business_tools'>Business Tools</a>
                    </div>
                </li>
                <li class='hide-2'>
                    <a href='/match'>Jobs</a>
                </li>
                <li class='hide-1'>
                    <a href='/featured-posts'>Stories</a>
                </li>
                <li data-click-toggle-class='show'>
                    <svg height='5' viewBox='0 0 19 5' width='19' xmlns='http://www.w3.org/2000/svg'>
                        <title>More</title>
                        <g fill-rule='evenodd' fill='#D8D8D8'>
                            <circle cx='2.5' cy='2.5' r='2.5'></circle>
                            <circle cx='9.5' cy='2.5' r='2.5'></circle>
                            <circle cx='16.5' cy='2.5' r='2.5'></circle>
                        </g>
                    </svg>
                    <div class='navmenu'>
                        <a class='hide show-6 badge' href='/news'>News</a>
                        <a class='hide show-5' href='/trending/tools'>Trending</a>
                        <a class='hide show-4' href='/stacks'>Stacks</a>
                        <a class='hide show-3' href='/categories'>Tools</a>
                        <a class='hide show-2' href='/match'>Job Search</a>
                        <a class='hide show-1' href='/featured-posts'>Stories</a>
                        <a href='/stackups'>Comparisons</a>
                        <a href='/posts'>Blog</a>
                        <a href='/explore'>Explore</a>
                        <a href='http://tooltime.stackshare.io'>Tool Time</a>
                    </div>
                </li>
            </ul>
            <div class='navbar__main'>
                <div class='search-navbar'>
                    <script>
                        function navbar_search() {
                            var input = document.getElementById('header-search')
                            var q = input.value
                            if (q)
                                window.location = '/search/q=' + encodeURIComponent(q)
                            return false
                        }
                    </script>
                    <div class='search-navbar'>
                        <form class='search' id='header-search-container' onsubmit='return navbar_search()'>
                            <input id='header-search' name='header-search' placeholder='Search'>
                        </form>
                        <div class='search-container search-arrow hidden'>
                            <a class='search-page' href='/search'>
                                <span class='octicon octicon-search dropdown-active-search'></span>
                                <span class='query'>type your search</span>
                            </a>
                            <div class='group-container'>
                                <div class='group group-services hidden'>
                                    <div class='header'>
                                        <span>Tools</span>
                                        <a href='#'>More</a>
                                    </div>
                                </div>
                                <div class='group group-stacks hidden'>
                                    <div class='header'>
                                        <span>Stacks</span>
                                        <a href='#'>More</a>
                                    </div>
                                </div>
                                <div class='group group-functions hidden'>
                                    <div class='header'>
                                        <span>Groups</span>
                                        <a href='#'>More</a>
                                    </div>
                                </div>
                                <div class='group group-categories hidden'>
                                    <div class='header'>
                                        <span>Categories</span>
                                        <a href='#'>More</a>
                                    </div>
                                </div>
                                <div class='group group-users hidden'>
                                    <div class='header'>
                                        <span>Users</span>
                                        <a href='#'>More</a>
                                    </div>
                                </div>
                                <div class='group group-stackups hidden'>
                                    <div class='header'>
                                        <span>Stackups</span>
                                        <a href='#'>More</a>
                                    </div>
                                </div>
                                <div class='group group-posts hidden'>
                                    <div class='header'>
                                        <span>Posts</span>
                                        <a href='#'>More</a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <a class='' data-target='#signinModal' data-toggle='modal' href='#' id='login-nav'
                   style='display: inline-flex;padding: 6px 0 6px 12px;'>
                    <span style="font-size: 14px;font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans !important;margin:auto 8px auto 0px;font-weight:normal">Sign up | Login</span>
                </a>
            </div>
        </div>
    </header>

    <style>
        .container {
            max-width: none;
            background-color: #FFF;
        }

        body {
            background-color: #FFF !important
        }

        #reviews-container li {
            border-left: 5px solid #EAEAEA;
            border-bottom: 0
        }

        #review {
            padding: 0px;
            margin-bottom: 60px
        }
    </style>
    <header class='service'>
        <div class='container'>
            <div class='row'>
                <ol class='breadcrumb col-md-10 col-xs-12 bread-nav' style='padding: 10px 0px 0px 15px;'>
                    <li class='active'>
                        <a class="btn btn-ss-g-alt btn-xs" itemprop="applicationSubCategory"
                           href="/languages">Languages</a>
                    </li>
                    <li>
                        <a itemprop="applicationCategory" href="/languages-and-frameworks">Languages &amp;
                            Frameworks</a>
                    </li>
                    <li>
                        <a href="/application_and_data">Application and Data</a>
                    </li>
                </ol>
            </div>
            <div class='row hidden-xs'>
                <br class='visible-sm'>
                <div class='div-center' style='display:none; margin-bottom: -30px;margin-top: 30px;'>
<span style='color:grey'>
StackShare helps you discover tools like
Java
</span>
                    <a class='btn btn-ss-gh-alt-nav' data-target='#signinModal' data-toggle='modal' href='#'
                       style='display: inline-flex;padding: 8px 5px;margin-left:20px;border-radius: 4px;'>
<span class='octicon octicon-mark-github' style='margin: auto 10px;display: inline-flex;font-size:22px'>
<span style="font-size: 14px;font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans !important;margin:auto 0 auto 10px;">Sign up / Login</span>
</span>
                    </a>
                </div>
                <br class='visible-sm'>
            </div>
            <div class='title-wrap' id='service-header'>
                <div class='row'>
                    <div class='col-md-9 col-xs-12'>
                        <div class='sp-service-logo col-md-2 col-xs-12'>
                            <a target="_blank" href="https://www.java.com"><img alt="image" itemprop="image"
                                                                                src="https://img.stackshare.io/service/995/K85ZWV2F.png"/></a>
                        </div>
                        <div class='service-wrap'>
                            <div class='col-md-10 col-xs-12' id='service-name'>
                                <a target="_blank" itemprop="name" href="https://www.java.com">Java</a>
                            </div>
                            <div class='col-md-10 col-xs-12' id='service-description-undertitle'>
                                <span itemprop='alternativeHeadline'>A concurrent, class-based, object-oriented, language specifically designed to have as few implementation dependencies as possible</span>
                            </div>
                        </div>
                    </div>
                    <div class='col-md-3 col-md-offset-0 col-sm-offset-4 col-sm-4 col-xs-offset-3 col-xs-6'
                         style='margin-top:20px'>
                        <div class='row'>
                            <div class='service-mob col-md-3 col-md-offset-0 col-sm-3 col-sm-offset-0 col-xs-9 col-xs-offset-3'>
                                <div class='service-trending-container'
                                     style='text-align: center;display: inline-block;'>
                                    <div class='service-fav' data-behavior='signin_on_click' style='color:black'>
                                        <i class='octicon octicon-star trending-star' id='fav'></i>
                                        <div class='star-count' id='service-995'>196</div>
                                        <span style='color:#333;font-size:10px'>Favorite</span>
                                    </div>
                                </div>
                            </div>
                            <div class='col-md-9 col-sm-9 col-xs-12' style='margin-bottom:10px;'>
                                <div>
                                    <div id='visit-website'><a data-toggle="modal" data-target="#signinModal"
                                                               class="btn btn-ss-alt btn-lg btn-block" href="#">I Use
                                        This</a></div>
                                    <div class='div-center website-block' style='margin-top:10px'><a target="_blank"
                                                                                                     id="visit-website"
                                                                                                     class="btn-block btn btn-ss-g-alt btn-xs"
                                                                                                     href="https://www.java.com">Visit
                                        Website</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <div class='row'>
        <div id='service-pills-nav'>
            <div class='container'>
                <ul class='nav nav-pills' role='tablist'>
                    <li class='active'>
                        <a data-href='/java/overview/ajax' data-target='#ajax-content' data-toggle='tabajax'
                           href='/java/overview' id='tab-link' role='tab'>
                            <div id='tab-label' itemprop='aggregateRating'>Votes</div>
                            2.45K
                        </a>
                    </li>
                    <li>
                        <a data-href='/java/fans/ajax' data-target='#ajax-content' data-toggle='tabajax'
                           href='/java/fans' id='tab-link' role='tab'>
                            <div id='tab-label'>Fans</div>
                            4.13K
                        </a>
                    </li>
                    <li>
                        <a data-href='/java/in-stacks/ajax' data-target='#ajax-content' data-toggle='tabajax'
                           href='/java/in-stacks' id='tab-link' role='tab'>
                            <div id='tab-label'>Stacks</div>
                            3.83K
                        </a>
                    </li>
                    <li>
                        <a data-href='/java/integrations/ajax' data-target='#ajax-content' data-toggle='tabajax'
                           href='/java/integrations' id='tab-link' role='tab'>
                            <div id='tab-label'>Integrations</div>
                            45
                        </a>
                    </li>
                    <li>
                        <a class='hint--top' data-align='left' data-hint='Check out jobs at companies that use Java'
                           href='/match/java' id='tab-link' role='tab'>
                            <span class='new-tab'>New</span>
                            <div id='tab-label'>Jobs</div>
                            4.45K
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class='tab-content' id='sp-stack-tab' style='margin: 30px 0 170px 0;'>
            <div class='tab-pane in active' id='ajax-content'>
                <div class='container'>
                    <div class='row'>
                        <div class='col-md-9 col-xs-12'>
                            <div class='row clearfix'>
                                <div class='row' style='margin:0'>
                                    <div style='padding: 28px'>
                                        <div id='service-description'>
                                            <span itemprop='about'>Java is a programming language and computing platform first released by Sun Microsystems in 1995. There are lots of applications and websites that will not work unless you have Java installed, and more are created every day. Java is fast, secure, and reliable. From laptops to datacenters, game consoles to scientific supercomputers, cell phones to the Internet, Java is everywhere!</span>
                                        </div>
                                        <div id='reasons'>
                                            <div class='section-title'>
                                                Why people like
                                                Java
                                            </div>
                                            <div id='reasons-container' style='margin: 0'>
                                                <div class='row' style='margin:0'>
                                                    <div id='reasons-section'>
                                                        <table style='width:100%'>
                                                            <tr>
                                                                <td class='row reasons-list' data-service_id='995'
                                                                    id='reasons-list-tile' style='padding:15px 0'>
                                                                    <div class='panel-group col-md-11 col-md-offset-1 col-sm-12 col-sm-offset-0'>
                                                                        <div class='reason_item' data-reason_id='3073'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3073/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="JkhgbXWf2CziK5B2NibK2NHKF5aLjo5ZcGfuggcd4uDxbhM6q7Bn9F75myRnKQvU6Or5LF0ihIUXYJio66LCAw=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3073'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>401</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Great libraries</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2349'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2349/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="nmlMQ3JO+5sYYVlmPOrje+drQgiAoTqSyL1ZxEtGRT9JTz8UrGFEQ6SzUjRt5SJ33kusslYNME6vui/up/ll3A=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2349'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>289</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Excellent tooling</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3077'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3077/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="eoBgEd5qSDktfXF8nWkK6ltxlMWeF5g6qgr4aJuRa9ytphNGAEX34ZGvei7MZsvmYlF6f0i7kubNDY5Cdy5LPw=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3077'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>288</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Widely used</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2351'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2351/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="e4CGYwtp2s2DJ01YzLgo+woCadEmSQ6dZhQk+UPpBdOspvU01UZlFT/1Rgqdt+n3MyKHa/DlBEEBE1LTr1YlMA=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2351'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>282</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Huge amount of documentation available</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2353'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2353/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="vUKGUiefmq47uesvebDw4aMx01Hb4zfRV8qL2JZ59VlqZPUF+bAldodr4H0ovzHtmhE96w1PPQ0wzf3yesbVug=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2353'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>234</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Large pool of developers available</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2352'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2352/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="996CX2UwE3STuoWj8yobFY1rwFWgt4s6FACh9MSXLHEg+PEIux+srC9ojvGiJdoZtEsu73YbgeZzB9feKCgMkg=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2352'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>151</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Excellent performance</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3071'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3071/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="5m60m4+c6izLTwG96SetEBLJX+GUMne+X8GnY1GDjq4xSMfMUbNV9HedCu+4KGwcK+mxW0KefWI4xtFJvTyuTQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3071'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>145</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Open source</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3078'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3078/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="IOQHonQmI7N61nTX0OChEH+cB2Y1OnQeZUsd1HygE3X3wnT1qgmca8YEf4WB72AcRrzp3OOWfsICTGv+kB8zlg=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3078'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>116</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Great development</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2350'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2350/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="INHwfXUt+/tcjA83jwBoXL8IPAUx3fwaZCRdgWFjWR/394MqqwJEI+BeBGXeD6lQhijSv+dx9sYDIyurjdx5/A=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2350'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>110</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Vast array of 3rd party libraries</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2348'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2348/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="Ad4xIiTLpVXScVKXMQwWS2rtAtJ2yuCxzo5t3EfGjZDW+EJ1+uQajW6jWcVgA9dHU83saKBm6m2piRv2q3mtcw=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2348'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>107</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Used for android</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>

                                                                    </div>
                                                                    <div class='col-md-11 col-md-offset-1 collapse more-reasons panel-collapse'>
                                                                        <div class='reason_item' data-reason_id='3072'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3072/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="pDDjmfSQqU4qG5M3sSxy6o/rYvoebehpLpn6wFR14I5zFpDOKr8WlpbJmGXgI7PmtsuMQMjB4rVJnozquMrAbQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3072'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>35</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Compiled Language </span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2356'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2356/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="9Tj1q5zWQq+97zr6sKIh/V3hSToGGV0eFpPgZ6ytT+QiHob8Qvn9dwE9MajhreDxZMGngNC1V8JxlJZNQBJvBw=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2356'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>29</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Native threads</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3076'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3076/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="OxJpbWWBYi1yZwdXeQcxxnsbmcAL12kBRZF9KrAJ+SLsNBo6u67d9c61DAUoCPDKQjt3et17Y90ilgsAXLbZwQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3076'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>29</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Used for Web</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3075'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3075/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="z8/hU8yT9Dnx/idtwdwopPv7LHX350j3D7zwcs49mf0Y6ZIEErxL4U0sLD+Q0+mowtvCzyFLQitou4ZYIoK5Hg=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3075'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>28</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>High Performance</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2355'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2355/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="/Gb9/4TmiTJfWBOL1isljkgObQ5BJ9AtHbTZtrocidErQI6oWsk26uOKGNmHJOSCcS6DtJeL2vF6s6+cVqOpMg=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2355'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>28</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Managed memory</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3474'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3474/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="E6IWIsToxkUvCJyL3sUhF4ibSB8iXW09dV7dZKZjyq7EhGV1Gsd5nZPal9mPyuAbsbumpfTxZ+ESWatOStzqTQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3474'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>23</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/3146/4707982.jpeg" alt="4707982" /&gt; &lt;span&gt;pyetti&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Statically typed</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3070'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3070/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="fXHKibWu8QNIFaElPFPpBdqCRCHiAw3cW5f/4DIUGY+qV7nea4FO2/THqndtXCgJ46KqmzSvBwA8kInK3qs5bA=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3070'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>20</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Great Community</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3567'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3567/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="7A3zt6HEkwkfFfOqftYL8tLBtbprAt+9W7S0bHHgsD07K4Dgf+ss0aPH+Pgv2cr+6+FbAL2u1WE8s8JGnV+Q3g=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3567'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>17</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/3448/3845827.jpeg" alt="3845827" /&gt; &lt;span&gt;cassioscofield&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;15&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Easy to read</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3690'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3690/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="KsUBIZKlk4pj7Y8zh+DF9d1KVeSq8s2Bmy+u9VAUAu3943J2TIosUt8/hGHW7wT55Gq7Xnxex138KNjfvKsiDg=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3690'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>16</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/3857/5018132.jpeg" alt="5018132" /&gt; &lt;span&gt;zlobjc&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Reliable platform</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3113'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3113/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="LNyHsorQK9A2Eb6VT2peT1VI0DeLCoX1N3RNWRC5xjP7+vTlVP+UCIrDtcceZZ9DbGg+jV2mjylQcztz/Abm0A=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3113'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>14</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/2343/6162563.jpeg" alt="6162563" /&gt; &lt;span&gt;spaceghost69&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Cross Platform Enterprise Integration</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='4294'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/4294/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="p5UnIX7ovYWYARpibYErq89rM3ZuXtvV5gAxT/V2M6Vws1R2oMcCXSTTETA8juqn9kvdzLjy0QmBB0dlGckTRg=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-4294'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>14</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/5543/625541.jpeg" alt="625541" /&gt; &lt;span&gt;ivanpierre&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;6&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>JVM compatibility</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2354'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2354/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="K7NWt1Azsl81YHp7UsddVQNHZXn9Nu3pIzkVFLrF0+j8lSXgjhwNh4mycSkDyJxZOmeLwyua5zVEPmM+VnrzCw=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2354'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>14</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Sturdy garbage collection</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3943'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3943/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="De3u/SjZDkyznGb5OK8njGyNde3txdAu8CzHvslUXfray52q9vaxlA9ObatpoOaAVa2bVztp2vKXK7GUJet9GQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3943'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>13</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/4641/510210.png" alt="510210" /&gt; &lt;span&gt;makarov-na&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Universal platform </span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='4036'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/4036/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="Wyt531ouSvIcFD9Q5fTHV09EoClLiudw443GTlAjKj2MDQqIhAH1KqDGNAK0+wZbdmROk50m7ayEirBkvJwK3g=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-4036'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>12</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1856/6329521.jpeg" alt="6329521" /&gt; &lt;span&gt;srikanthmalyala&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Good amount of APIs</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3074'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3074/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="39qyPo7QXa7rxcS5XeYN+rtraXK0TCyKRzd4wnsj3moI/MFpUP/idlcXz+sM6cz2gkuHyGLgJlYgMA7ol5z+iQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3074'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>10</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/1418/12291908_10153793408511913_738447819807183112_o.jpg" alt="12291908 10153793408511913 738447819807183112 o" /&gt; &lt;span&gt;sahin&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;1667&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Great Support</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='2347'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/2347/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="nKSxdUVgql3jRYUvZU3/DYpHgTqttfbjyPSJR/D1MYZLgsIim08VhV+Xjn00Qj4Bs2dvgHsZ/D+v8/9tHEoRZQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-2347'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>9</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/317/1016365.jpeg" alt="1016365" /&gt; &lt;span&gt;gdi2290&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3277&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Lots of boilerplate</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='3941'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/3941/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="cptQrJTvRM4jA/NtS60gYPPy3YYXwGJ4U8nsJ4vXUYelvSP7SsD7Fp/R+D8aouFsytIzPMFsaKQ0zpoNZ2hxZA=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-3941'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>6</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/4620/1284997.jpeg" alt="1284997" /&gt; &lt;span&gt;GjorgjiJosifov&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Backward compatible</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='9629'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/9629/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="KtyxcJn8UZIpu3LXHnnD0iFchv909z5S/JjeJ1Alv8D9+sInR9PuSpVpeYVPdgLeGHxoRaJbNI6bn6gNvJqfIw=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-9629'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>3</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/103200/8378882.png" alt="8378882" /&gt; &lt;span&gt;nraghu99&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Great ecosystem</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='5496'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/5496/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="nXEinXUDq9d4oazJ384E7vdwDZ878W4ocXcVY0XwWKtKV1HKqywUD8Rzp5uOwcXizlDjJe1dZPQWcGNJqU94SA=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-5496'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>3</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/8861/800050.png" alt="800050" /&gt; &lt;span&gt;nprudhomme&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;3&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Everywhere</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='7496'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/7496/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="5Mr0zxVTESs245VBuuWkp57VDtSxnNnMG6/v3zmyK+oz7IeYy3yu84oxnhPr6mWrp/Xgbmcw0xB8qJn11Q0LCQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-7496'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>2</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/31220/12066639.jpeg" alt="12066639" /&gt; &lt;span&gt;indiagator&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;6&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>excellent SDK - JDK</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='8093'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/8093/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="3NUErJoFTRdktbUxETZT+fSuydVE/CDB3nhhjrcwNfYL83f7RCryz9hnvmNAOZL1zY4nb5JQKh25fxekW48VFQ=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-8093'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>2</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/55746/3253772.png" alt="3253772" /&gt; &lt;span&gt;mavbozo&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;18&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Clojure</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                        <div class='reason_item' data-reason_id='8486'
                                                                             style='margin: 0px 0 6px 5px;  display: inline-block;'>
                                                                            <form id="reason-vote"
                                                                                  style="margin-bottom:0px"
                                                                                  action="/reasons/8486/toggle_vote"
                                                                                  accept-charset="UTF-8"
                                                                                  data-remote="true" method="post">
                                                                                <input name="utf8" type="hidden"
                                                                                       value="&#x2713;"/><input
                                                                                    type="hidden"
                                                                                    name="authenticity_token"
                                                                                    value="3HbgEi0+YObENF8W8G9FFUcd2pc6k4xFRslJEpOrdHULUJNF8xHfPnjmVEShYIQZfj00Lew/hpkhzj84fxRUlg=="/>
                                                                                <label style='margin:0'>
                                                                                    <div class='reason-vote-container'>
                                                                                        <input type="checkbox"
                                                                                               name="vote" id="vote"
                                                                                               value="1"
                                                                                               data-behavior="signin_on_click"/>
                                                                                        <span class='reason-count'
                                                                                              id='reason-8486'
                                                                                              itemprop='upvoteCount'
                                                                                              style='text-align: center'>1</span>
                                                                                    </div>
                                                                                    <span style='display: flex;height: 38px;'>
<div id='reason-text' style='margin: auto 0 auto 6px;'>
<span class='reason-author-pop'
      data-content='&lt;img src="https://img.stackshare.io/user/16138/346873.jpeg" alt="346873" /&gt; &lt;span&gt;albogdano&lt;/span&gt; &lt;span class=&#x0027;user-score-hover&#x0027;&gt;15&lt;/span&gt;'
      data-toggle='popover' itemprop='comment' rel='popover' role='button' style='max-width:30px !important'
      title='Submitted By'>Better than Ruby</span>
</div>
</span>
                                                                                </label>
                                                                            </form>

                                                                        </div>
                                                                    </div>
                                                                    <div class='col-md-11 col-md-offset-1 col-sm-12 col-sm-offset-0'
                                                                         style='margin-top:10px;padding-left:60px;'>
                                                                        <a class='section-title show-more-reasons'
                                                                           data-parent='#reasons-list-tile'
                                                                           data-target='.more-reasons'
                                                                           data-text-original='Show more one-liners'
                                                                           data-text-swap='Show fewer one-liners'
                                                                           data-toggle='collapse' href='#more'
                                                                           id='show-less'
                                                                           onclick="$('.more-reasons').fadeToggle();">
                                                                            <span class='octicon octicon-ellipsis'
                                                                                  style='font-size: 45px;color: rgb(173, 173, 173);'></span>
                                                                        </a>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class='row'>
                                                    <div class='add-reason col-md-6 col-md-offset-2 col-sm-offset-1 col-sm-6'
                                                         style='border-right: 1px solid #ebebeb'>
                                                        <div class='add-reason-title'>
                                                            Add a one-liner
                                                        </div>
                                                        <form class="new_reason" id="new_reason" action="/reasons"
                                                              accept-charset="UTF-8" data-remote="true" method="post">
                                                            <input name="utf8" type="hidden" value="&#x2713;"/>
                                                            <div class='hint--top' data-align='left'
                                                                 data-hint='eg "Easy setup", "Great customer support", or "GitHub integration"'
                                                                 id='reason-input'>
                                                                <input class="form-control" id="new-reason-text"
                                                                       data-behavior="signin_on_click"
                                                                       placeholder="Why do you like using Java?"
                                                                       style="width:280px" maxlength="55" size="55"
                                                                       type="text" name="reason[text]"/>
                                                                <br>
                                                                <div id='btn-responsive'><input type="submit"
                                                                                                name="commit"
                                                                                                value="Submit"
                                                                                                class="btn btn-ss hint--top"
                                                                                                style="margin:15px 0 10px 0"/>
                                                                </div>
                                                            </div>
                                                            <input type="hidden" value="995" name="reason[service_id]"
                                                                   id="reason_service_id"/>
                                                        </form>
                                                    </div>
                                                    <div class='add-reason col-md-4 col-sm-5'
                                                         style='text-align:center;margin-left:0px'>
                                                        <div style='margin-bottom:50px;margin-top:20px'>
                                                            <div id='btn-responsive'><a data-toggle="modal"
                                                                                        data-target="#signinModal"
                                                                                        class="btn btn-ss hint--top"
                                                                                        style="margin-bottom: 10px;margin-top:22px;"
                                                                                        data-hint="Tell other developers about your experience with Java"
                                                                                        data-align="left" href="#">Add a
                                                                review</a></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                        </div>
                                    </div>
                                    <div style='padding: 0px 15px'>
                                        <div class='row' style='margin-bottomX:20px'>
                                            <div style='padding: 15px 30px'>
                                                <div class='section-title' id='stacks'>
                                                    Companies using
                                                    Java
                                                </div>
                                            </div>
                                        </div>
                                        <div class='row' style='padding: 0 30px'>
                                            <div class='col-md-12'>
                                                <div class='row' style='margin-bottom: 8px;'>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Testing at Turbonomic" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/turbonomic/testing-at-turbonomic"><img
                                                                alt="Testing at Turbonomic"
                                                                src="https://img.stackshare.io/stack/52718/turbonomic-logo.jpg"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Teracy" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/teracy/teracy"><img alt="Teracy"
                                                                                      src="https://img.stackshare.io/stack/2597/1375a58db3c6740f3afec0a7d8776064.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Artoo" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/artoo/artoo"><img alt="Artoo"
                                                                                    src="https://img.stackshare.io/stack/2052/YWke7U-D.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="AdNear" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/adnear/adnear"><img alt="AdNear"
                                                                                      src="https://img.stackshare.io/stack/2060/RXPjyodE.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="PT Midtrans" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/pt-midtrans/pt-midtrans"><img alt="PT Midtrans"
                                                                                                src="https://img.stackshare.io/stack/32964/New_Logo_Veritrans.jpg"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Adsia" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/adsia/adsia"><img alt="Adsia"
                                                                                    src="https://img.stackshare.io/stack/2130/72a790d42b03341f9f1984b6e17975e2.jpeg"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Netflix" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/netflix/netflix"><img alt="Netflix"
                                                                                        src="https://img.stackshare.io/stack/34/2lxo5V22.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="ebay" data-align="left" style="margin-bottom:10px"
                                                           class="hint--top" href="/ebay/ebay"><img alt="ebay"
                                                                                                    src="https://img.stackshare.io/stack/26/NCDCFXva.jpeg"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Sematext Cloud" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/sematext/sematext-cloud"><img alt="Sematext Cloud"
                                                                                                src="https://img.stackshare.io/stack/3540/logo-200x200.png"/></a>
                                                    </div>
                                                    <div class='more-stacks-sp'>
                                                        <a href="/java/in-stacks">+ 1499 more</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='similar-service-logos' style='padding: 0px 15px 0 15px;'>
                                        <div class='row'>
                                            <div style='padding: 15px 30px'>
                                                <div class='section-title'>
                                                    Java
                                                    integrates with
                                                </div>
                                            </div>
                                        </div>
                                        <div class='row' style='padding: 0 30px;'>
                                            <div class='col-md-12'>
                                                <div class='row' style='margin-bottom: 8px;'>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Android SDK" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top" href="/android"><img
                                                                alt="Android SDK" class="company-icon"
                                                                src="https://img.stackshare.io/service/1010/m8jf0po4imu8t5eemjdd.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="IntelliJ IDEA" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/intellij-idea"><img alt="IntelliJ IDEA"
                                                                                      class="company-icon"
                                                                                      src="https://img.stackshare.io/service/1453/icon_IntelliJIDEA.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Spring" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/spring"><img alt="Spring" class="company-icon"
                                                                               src="https://img.stackshare.io/service/996/SpringFramework_Twitter_Avatar-NoBorder.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Eclipse" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top" href="/eclipse"><img
                                                                alt="Eclipse" class="company-icon"
                                                                src="https://img.stackshare.io/service/1446/8cyY6D_m.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Scala" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/scala"><img alt="Scala" class="company-icon"
                                                                              src="https://img.stackshare.io/service/1012/dc47c969494d3279783610ba3298f58b.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Rollbar" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top" href="/rollbar"><img
                                                                alt="Rollbar" class="company-icon"
                                                                src="https://img.stackshare.io/service/328/nBpux_k2_400x400.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Clojure" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top" href="/clojure"><img
                                                                alt="Clojure" class="company-icon"
                                                                src="https://img.stackshare.io/service/1003/Clojure_300x300.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Airbrake" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/airbrake"><img alt="Airbrake" class="company-icon"
                                                                                 src="https://img.stackshare.io/service/194/c4v8DgiK.png"/></a>
                                                    </div>
                                                    <div class='col-md-1 stack-logo' style='margin:0 5px'>
                                                        <a data-hint="Spring-Boot" data-align="left"
                                                           style="margin-bottom:10px" class="hint--top"
                                                           href="/spring-boot"><img alt="Spring-Boot"
                                                                                    class="company-icon"
                                                                                    src="https://img.stackshare.io/service/2927/992d3596458fca87741b8e93e7df0860_normal.png"/></a>
                                                    </div>
                                                    <div class='more-stacks-sp'>
                                                        <a href="/java/integrations">+ 36 more</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <!-- - if @service.blog_posts.any? -->
                                    <!-- = render 'services/blog_posts' -->
                                    <br>
                                    <br>
                                    <div class='row' style='margin:0'>
                                        <div class='col-md-12' style='padding: 10px 30px'>
                                            <div class='section-title'>
                                                Explore other
                                                <b>Languages &amp; Frameworks</b>
                                                tools that are known for:
                                            </div>
                                            <div id='service-features'>
                                                <ul></ul>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/great-libraries">Great
                                                        libraries</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/widely-used">Widely
                                                        used</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/open-source">Open
                                                        source</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/high-performance">High
                                                        Performance</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/statically-typed">Statically
                                                        typed</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/great-community">Great
                                                        Community</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/easy-to-read">Easy
                                                        to read</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/great-support">Great
                                                        Support</a></b>
                                                </li>
                                                <li>
                                                    <b><a href="/top-tools/languages-and-frameworks/everywhere">Everywhere</a></b>
                                                </li>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class='row' style='padding:20px'>
                                    <div class='section-title similar' style='text-align:center;margin:11px 0'>
                                        <a href="/languages">Similar Tools &amp; Services</a>
                                    </div>
                                </div>
                                <div class='row' style='padding: 0 30px'>
                                    <div class='col-md-12'>
                                        <div class='row similar-services-items' style='margin-left:0'>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Ruby" data-align="left" class="hint--top"
                                                   href="/ruby"><img alt="Ruby" class="company-icon"
                                                                     src="https://img.stackshare.io/service/989/ruby.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/ruby">Ruby</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="PHP" data-align="left" class="hint--top" href="/php"><img
                                                        alt="PHP" class="company-icon"
                                                        src="https://img.stackshare.io/service/991/php.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/php">PHP</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Python" data-align="left" class="hint--top"
                                                   href="/python"><img alt="Python" class="company-icon"
                                                                       src="https://img.stackshare.io/service/993/pUBY5pVj.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/python">Python</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Groovy" data-align="left" class="hint--top"
                                                   href="/groovy"><img alt="Groovy" class="company-icon"
                                                                       src="https://img.stackshare.io/service/997/6d180ca6647e7f690c2615a86e7c2843.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/groovy">Groovy</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Clojure" data-align="left" class="hint--top"
                                                   href="/clojure"><img alt="Clojure" class="company-icon"
                                                                        src="https://img.stackshare.io/service/1003/Clojure_300x300.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/clojure">Clojure</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Erlang" data-align="left" class="hint--top"
                                                   href="/erlang"><img alt="Erlang" class="company-icon"
                                                                       src="https://img.stackshare.io/service/1004/cbdf77412da183e43d41c0c0f9a7005a.jpeg"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/erlang">Erlang</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Go" data-align="left" class="hint--top" href="/go"><img
                                                        alt="Go" class="company-icon"
                                                        src="https://img.stackshare.io/service/1005/go.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/go">Go</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Objective-C" data-align="left" class="hint--top"
                                                   href="/objective-c"><img alt="Objective-C" class="company-icon"
                                                                            src="https://img.stackshare.io/service/1008/xcode.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/objective-c">Objective-C</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Swift" data-align="left" class="hint--top"
                                                   href="/swift"><img alt="Swift" class="company-icon"
                                                                      src="https://img.stackshare.io/service/1009/tuHsaI2U.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/swift">Swift</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Scala" data-align="left" class="hint--top"
                                                   href="/scala"><img alt="Scala" class="company-icon"
                                                                      src="https://img.stackshare.io/service/1012/dc47c969494d3279783610ba3298f58b.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/scala">Scala</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Visual Basic" data-align="left" class="hint--top"
                                                   href="/visual-basic"><img alt="Visual Basic" class="company-icon"
                                                                             src="https://img.stackshare.io/service/1016/10ffe9eda0de4670955cc9bdfa78fe9b.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/visual-basic">Visual Basic</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="C#" data-align="left" class="hint--top"
                                                   href="/c-sharp"><img alt="C#" class="company-icon"
                                                                        src="https://img.stackshare.io/service/1015/10ffe9eda0de4670955cc9bdfa78fe9b.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/c-sharp">C#</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Perl" data-align="left" class="hint--top"
                                                   href="/perl"><img alt="Perl" class="company-icon"
                                                                     src="https://img.stackshare.io/service/1048/perl.jpg"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/perl">Perl</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="C++" data-align="left" class="hint--top"
                                                   href="/cplusplus"><img alt="C++" class="company-icon"
                                                                          src="https://img.stackshare.io/service/1049/cplusplus.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/cplusplus">C++</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="JRuby" data-align="left" class="hint--top"
                                                   href="/jruby"><img alt="JRuby" class="company-icon"
                                                                      src="https://img.stackshare.io/service/1068/jruby-logo-standard-logo-stroke-large.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/jruby">JRuby</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Haskell" data-align="left" class="hint--top"
                                                   href="/haskell"><img alt="Haskell" class="company-icon"
                                                                        src="https://img.stackshare.io/service/1069/oCgm29k9.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/haskell">Haskell</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Rust" data-align="left" class="hint--top"
                                                   href="/rust"><img alt="Rust" class="company-icon"
                                                                     src="https://img.stackshare.io/service/1070/v7txhrjp9pdqrkdtxxp0.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/rust">Rust</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="C" data-align="left" class="hint--top" href="/c"><img
                                                        alt="C" class="company-icon"
                                                        src="https://img.stackshare.io/service/1081/no-img-open-source.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/c">C</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Markdown" data-align="left" class="hint--top"
                                                   href="/markdown"><img alt="Markdown" class="company-icon"
                                                                         src="https://img.stackshare.io/service/1147/OeMUNnaE.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/markdown">Markdown</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="HAML" data-align="left" class="hint--top"
                                                   href="/haml"><img alt="HAML" class="company-icon"
                                                                     src="https://img.stackshare.io/service/1169/Picture_2.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/haml">HAML</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="CoffeeScript" data-align="left" class="hint--top"
                                                   href="/coffeescript"><img alt="CoffeeScript" class="company-icon"
                                                                             src="https://img.stackshare.io/service/1178/slQydAMv.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/coffeescript">CoffeeScript</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Hack" data-align="left" class="hint--top"
                                                   href="/hack"><img alt="Hack" class="company-icon"
                                                                     src="https://img.stackshare.io/service/1208/GIWkiyGD.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/hack">Hack</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="JavaScript" data-align="left" class="hint--top"
                                                   href="/javascript"><img alt="JavaScript" class="company-icon"
                                                                           src="https://img.stackshare.io/service/1209/javascript.jpeg"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/javascript">JavaScript</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="COBOL" data-align="left" class="hint--top"
                                                   href="/cobol"><img alt="COBOL" class="company-icon"
                                                                      src="https://img.stackshare.io/service/1210/no-img-open-source.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/cobol">COBOL</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Common Lisp" data-align="left" class="hint--top"
                                                   href="/common-lisp"><img alt="Common Lisp" class="company-icon"
                                                                            src="https://img.stackshare.io/service/1211/lisp.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/common-lisp">Common Lisp</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Arc" data-align="left" class="hint--top" href="/arc"><img
                                                        alt="Arc" class="company-icon"
                                                        src="https://img.stackshare.io/service/1212/no-img-open-source.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/arc">Arc</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="R" data-align="left" class="hint--top" href="/r"><img
                                                        alt="R" class="company-icon"
                                                        src="https://img.stackshare.io/service/1213/r-logo.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/r">R</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="MATLAB" data-align="left" class="hint--top"
                                                   href="/matlab"><img alt="MATLAB" class="company-icon"
                                                                       src="https://img.stackshare.io/service/1214/h5g3etjnacmazg8oq17z.jpeg"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/matlab">MATLAB</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Dart" data-align="left" class="hint--top"
                                                   href="/dart"><img alt="Dart" class="company-icon"
                                                                     src="https://img.stackshare.io/service/1646/Twitter-02.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/dart">Dart</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="F#" data-align="left" class="hint--top"
                                                   href="/f-sharp"><img alt="F#" class="company-icon"
                                                                        src="https://img.stackshare.io/service/2155/yRNL7yTW.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/f-sharp">F#</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Jolie" data-align="left" class="hint--top"
                                                   href="/jolie"><img alt="Jolie" class="company-icon"
                                                                      src="https://img.stackshare.io/service/2503/cEo1tH0G.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/jolie">Jolie</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="HTML5" data-align="left" class="hint--top"
                                                   href="/html5"><img alt="HTML5" class="company-icon"
                                                                      src="https://img.stackshare.io/service/2538/kEpgHiC9.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/html5">HTML5</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="D" data-align="left" class="hint--top" href="/d"><img
                                                        alt="D" class="company-icon"
                                                        src="https://img.stackshare.io/service/3117/d-5.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/d">D</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Crystal" data-align="left" class="hint--top"
                                                   href="/crystal"><img alt="Crystal" class="company-icon"
                                                                        src="https://img.stackshare.io/service/3381/_iXHOD-1.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/crystal">Crystal</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Elixir" data-align="left" class="hint--top"
                                                   href="/elixir"><img alt="Elixir" class="company-icon"
                                                                       src="https://img.stackshare.io/service/1974/drop.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/elixir">Elixir</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Lua" data-align="left" class="hint--top" href="/lua"><img
                                                        alt="Lua" class="company-icon"
                                                        src="https://img.stackshare.io/service/2118/lua.gif"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/lua">Lua</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Scala Native" data-align="left" class="hint--top"
                                                   href="/scala-native"><img alt="Scala Native" class="company-icon"
                                                                             src="https://img.stackshare.io/service/5208/e4TKXFTn.jpg"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/scala-native">Scala Native</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Julia" data-align="left" class="hint--top"
                                                   href="/julia"><img alt="Julia" class="company-icon"
                                                                      src="https://img.stackshare.io/service/3914/647b7cf75cb5662b96901bc521968e94.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/julia">Julia</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Elm" data-align="left" class="hint--top" href="/elm"><img
                                                        alt="Elm" class="company-icon"
                                                        src="https://img.stackshare.io/service/4009/cZHhsF-c_normal.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/elm">Elm</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="ES6" data-align="left" class="hint--top" href="/es6"><img
                                                        alt="ES6" class="company-icon"
                                                        src="https://img.stackshare.io/service/4109/16407404782_8b9c57eab3.jpg"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/es6">ES6</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Kotlin" data-align="left" class="hint--top"
                                                   href="/kotlin"><img alt="Kotlin" class="company-icon"
                                                                       src="https://img.stackshare.io/service/3750/pCfEzr6L.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/kotlin">Kotlin</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Eve" data-align="left" class="hint--top" href="/eve"><img
                                                        alt="Eve" class="company-icon"
                                                        src="https://img.stackshare.io/service/5985/qbJoNuap.png"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/eve">Eve</a>
                                                </div>
                                            </div>
                                            <div class='col-md-1 stack-logo' style='padding:0;margin:0 20px 20px 20px;'>
                                                <a data-hint="Eta" data-align="left" class="hint--top" href="/eta"><img
                                                        alt="Eta" class="company-icon"
                                                        src="https://img.stackshare.io/service/6490/2OjlqE5m.jpg"/></a>
                                                <div class='row similar-services-items'>
                                                    <a href="/eta">Eta</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class='row'><a class="btn btn-ss-alt"
                                                style="padding: 10px;float:right;margin-bottom:20px;margin-right:30px"
                                                href="/claim-service/java">Claim this page</a></div>
                        </div>
                        <div class='col-md-3 col-sm-12 col-xs-12' style='padding:0'>
                            <div class='page-container-sp col-md-12' data-offset-bottom='845' data-offset-top='350'
                                 data-spy='affix' id='sp-sidebar' style='width:275px;z-index:0'>
                                <br>
                                <div class='section-title-2' style='padding:15px 10px 0 10px;margin:0'>
                                    See
                                    Java
                                    's Stacks
                                </div>
                                <div class='div-center service-stack-link'>
                                    <a class='hint hint--left' data-align='left' data-hint='See what powers Java'
                                       href='/java/stacks'>
                                        <span class='glyphicon glyphicon-align-justify'
                                              style='font-size: 26px;padding-top: 5px;'></span>
                                    </a>
                                </div>
                                <hr style='margin-bottom:0'>
                                <div class='stack-navigator'>
                                    <div class='row'>
                                        <div class='row' style='padding-top:0px'>
                                            <div style='padding:0 18px'></div>
                                            <div id='sponsored-stack'
                                                 style='padding: 20px;margin: 0 45px;border-radius: 5px;'>
                                                <div class='sponsored-function'>
                                                    <div class='row section-title' style='margin-bottom:7px'>
                                                        Sponsored Tool
                                                    </div>
                                                    <div class='row service-logo div-center service-sponsor-img'><a
                                                            id="Service: Autosend, Sponsor: Sentry"
                                                            data-hint="Learn more about Sentry" data-align="left"
                                                            class="hint hint--top" href="/sentry"><img
                                                            style="width:63px;height:63px"
                                                            src="https://img.stackshare.io/service/191/lzoDXqf-.png"
                                                            alt="Lzodxqf "/></a></div>
                                                    <div class='row'
                                                         style='padding-top:8px;color:grey;font-size:14px;font-weight:600'>
                                                        <a data-hint="Learn more about Sentry" data-align="left"
                                                           class="hint hint--top" href="/sentry">Sentry</a>
                                                    </div>
                                                    <div class='row' style='padding-top:8px'>
                                                        <div class='sponsored-function-name-post hidden-xs'>Exception
                                                            Monitoring
                                                        </div>
                                                    </div>
                                                    <div class='row'>
                                                        <a class="btn btn-ss-g-a btn-xs hint hint--top"
                                                           style="margin-top: 10px;" id="sponsored-website-url"
                                                           target="_blank"
                                                           data-hint="https://sentry.io/welcome/?utm_source=stackshare&amp;utm_medium=cpc&amp;utm_campaign=stackshare-september"
                                                           data-align="left"
                                                           href="https://sentry.io/welcome/?utm_source=stackshare&amp;utm_medium=cpc&amp;utm_campaign=stackshare-september">Visit
                                                            Sentry&#39;s Website</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div style='padding:0 18px'>
                                                <hr style='margin:0'>
                                            </div>
                                        </div>

                                    </div>
                                    <br>
                                    <div class='div-center'>
                                        <a class="btn btn-ss" style="margin-top:0" href="/languages/stackups">See
                                            comparisons of similar tools</a>
                                        <a class="sp-category-stackups" href="/languages-and-frameworks/stackups">See
                                            comparisons of related tools</a>
                                    </div>
                                    <br>
                                    <div class='section-title-2' style='margin-bottom:none'>
                                        Group
                                    </div>
                                    <div class='div-center'>
                                        <a class="btn btn-ss-g btn-xs" itemprop="applicationSubCategory"
                                           href="/languages">Languages</a>
                                    </div>
                                    <br>
                                    <div class='section-title-2' style='margin-bottom:none'>
                                        Layer
                                    </div>
                                    <div class='navigation-layers'>
                                        <div class='current-layer'>
                                            <div class='application_and_data stack-layer'>
                                                <div class='stack-layer-title stack-layer-title-tag'><a
                                                        href="/application_and_data">Application and Data</a></div>
                                                <div class='row' style='margin-left:0;padding-top: 5px;'></div>
                                            </div>
                                        </div>
                                        <div class='show-layer stack-layer utilities'>
                                            <div class='stack-layer-title stack-layer-title-tag'><a href="/utilities">Utilities</a>
                                            </div>
                                            <div class='row' style='margin-left:0;padding-top: 5px;'></div>
                                        </div>
                                        <div class='devops show-layer stack-layer'>
                                            <div class='stack-layer-title stack-layer-title-tag'><a href="/devops">DevOps</a>
                                            </div>
                                            <div class='row' style='margin-left:0;padding-top: 5px;'></div>
                                        </div>
                                        <div class='business_tools show-layer stack-layer'>
                                            <div class='stack-layer-title stack-layer-title-tag'><a
                                                    href="/business_tools">Business Tools</a></div>
                                            <div class='row' style='margin-left:0;padding-top: 5px;'></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id='sp-sidebar-mobile'>
                                <div class='section-title-2' style='padding:15px 10px 0 10px;margin:0'>
                                    See
                                    Java
                                    's Stacks
                                </div>
                                <div class='div-center service-stack-link'>
                                    <a href='/java/stacks'>
                                        <span class='glyphicon glyphicon-align-justify'
                                              style='font-size: 16px;padding-top: 5px;'></span>
                                    </a>
                                </div>
                                <div class='row'>
                                    <div class='col-sm-6 col-xs-12'>
                                        <div class='row'>
                                            <div class='col-sm-offset-1 col-sm-10 col-xs-offset-1 col-xs-10'>
                                                <div class='stack-navigator'>
                                                    <div class='section-title-2'>
                                                        Stack Layer
                                                    </div>
                                                    <div class='navigation-layers'>
                                                        <div class='current-layer'>
                                                            <div class='application_and_data stack-layer'>
                                                                <div class='stack-layer-title stack-layer-title-tag'><a
                                                                        href="/application_and_data">Application and
                                                                    Data</a></div>
                                                                <div class='row'
                                                                     style='margin-left:0;padding-top: 5px;'></div>
                                                            </div>
                                                        </div>
                                                        <div class='show-layer stack-layer utilities'>
                                                            <div class='stack-layer-title stack-layer-title-tag'><a
                                                                    href="/utilities">Utilities</a></div>
                                                            <div class='row'
                                                                 style='margin-left:0;padding-top: 5px;'></div>
                                                        </div>
                                                        <div class='devops show-layer stack-layer'>
                                                            <div class='stack-layer-title stack-layer-title-tag'><a
                                                                    href="/devops">DevOps</a></div>
                                                            <div class='row'
                                                                 style='margin-left:0;padding-top: 5px;'></div>
                                                        </div>
                                                        <div class='business_tools show-layer stack-layer'>
                                                            <div class='stack-layer-title stack-layer-title-tag'><a
                                                                    href="/business_tools">Business Tools</a></div>
                                                            <div class='row'
                                                                 style='margin-left:0;padding-top: 5px;'></div>
                                                        </div>
                                                    </div>
                                                    <div class='section-title-2'>
                                                        Category
                                                    </div>
                                                    <div class='current-category'><a href="/languages-and-frameworks">Languages
                                                        &amp; Frameworks</a></div>
                                                    <div class='section-title-2'>
                                                        Function
                                                    </div>
                                                    <div class='current-category'><a href="/languages">Languages</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class='col-sm-6 col-xs-12'>
                                        <div class='row'>
                                            <div class='col-offset-0 col-sm-10 col-xs-offset-1 col-xs-10'>
                                                <div class='div-center' style='margin: 25px 0 0px 0;'>
                                                    <a href='/trending/tools' style='display: inline-flex;'>
<span class='octicon octicon-flame' style='display: inline-flex;font-size:21px;margin-bottom: 20px;'>
<span style="font-size: 12px;font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans !important;margin:auto 0 auto 3px;">See what's trending</span>
</span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div id='push'></div>
</div>
<footer>
    <div class='container'>
        <div class='row'>
            <div class='col-md-10 col-md-offset-1 col-sm-12 col-sm-offset-0 col-xs-10 col-xs-offset-1'>
                <div class='row'>
                    <div class='col-md-3 col-sm-3 col-xs-12'>
                        <div class='footer-links'>
                            <div class='row' style='margin:0;'>
<span style='margin-bottom:10px'>
<div class='footer-link-heading'>Tools & Services</div>
<div><a title="Search Tools &amp; Services" href="/search">Search</a></div>
<div><a title="Technology Stacks &amp; Tools" href="/explore">Explore</a></div>
<div><a title="Technology Tools &amp; Services" href="/categories">Browse Categories</a></div>
<div><a title="Application &amp; Data" href="/application_and_data">Application &amp; Data</a></div>
<div><a title="Utilities" href="/utilities">Utilities</a></div>
<div><a title="DevOps" href="/devops">DevOps</a></div>
<div><a title="Business Tools" href="/business_tools">Business Tools</a></div>
<div><a title="Submit a Tool" href="/submit">Submit A Tool</a></div>
<div><a title="Tool Time" href="//tooltime.stackshare.io">Tool Time</a></div>
</span>
                            </div>
                        </div>
                    </div>
                    <div class='col-md-3 col-sm-3 col-xs-12'>
                        <div class='footer-links'>
                            <div class='row' style='margin:0;'>
<span style='margin-bottom:10px'>
<div class='footer-link-heading'>Stacks</div>
<div>
<a title="Stack Match" href="/match">Job Search</a>
<a href='/match.atom'>
<i class='fa fa-rss-square' style='color: #ffffff; margin-left: 5px;'></i>
</a>
</div>
<div><a title="Technology Stacks" href="/stacks">Stacks</a></div>
<div><a title="Trending Stacks" href="/trending/stacks">Trending Stacks</a></div>
<div><a title="Companies" href="/companies">Companies</a></div>
<div><a title="Featured Posts" href="/featured-posts">Featured Posts</a></div>
<div><a title="Add Your Tech Stack" href="/onboarding/scan">Add Your Stack</a></div>
<div>
<a href="/posts/introducing-stack-embed-display-your-stack-on-medium-linkedin-or-any-website">Stack Embed</a>
</div>
</span>
                            </div>
                        </div>
                    </div>
                    <div class='col-md-3 col-sm-3 col-xs-12'>
                        <div class='footer-links'>
                            <div class='row' style='margin:0;'>
<span style='margin-bottom:10px'>
<div class='footer-link-heading'>Company</div>
<div><a title="StackShare API" href="//api.stackshare.io">API</a></div>
<div><a title="StackShare Blog" href="/posts">Blog</a></div>
<div>
<a title="Careers at StackShare" href="/careers">Careers</a>
<span class='hidden-xs hidden-sm hidden-md'
      style='margin-left:5px;padding: 3px 10px;font-size: 75%;font-weight: 600;color: white;border-radius: .25em;background-color: #0690fa;font-size: 12px;'>We're Hiring!</span>
</div>
<div><a title="Vendors" href="/vendors">Vendors</a></div>
<div><a title="Our Stack" href="/stackshare">Our Stack</a></div>
<div><a title="StackShare on AngelList" href="https://angel.co/stackshare">AngelList</a></div>
<div><a title="Contact Us" href="mailto:team@stackshare.io">Contact Us</a></div>
<div><a title="Logos &amp; Branding" href="/branding">Logos &amp; Branding</a></div>
</span>
                            </div>
                        </div>
                    </div>
                    <div class='col-md-3 col-sm-3 col-xs-12'>
                        <div class='footer-links'>
                            <div class='row'>
                                <div class='footer-link-heading'>Follow Us</div>
                                <ul>
                                    <li>
                                        <a class='social-button twitter' href='https://twitter.com/stackshareio'
                                           target='_blank'>
                                            <i class='fa fa-twitter-square fa-2x'></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a class='social-button facebook' href='https://facebook.com/stackshareio'
                                           target='_blank'>
                                            <i class='fa fa-facebook-square fa-2x'></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a class='social-button linkedin'
                                           href='https://www.linkedin.com/company/stackshare' target_='_blank'>
                                            <i class='fa fa-linkedin-square fa-2x'></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a class='social-button angellist' href='https://angel.co/stackshare'
                                           target_='_blank'>
                                            <i class='fa fa-angellist fa-2x'></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class='row div-center' style='margin-bottom:50px'>
            <div class='div-center'><a href="/"><img alt="stackshare" style="max-width: 135px;margin-bottom:5px"
                                                     src="/assets/stackshare-logo-grey-b460a99820c6d94df6b22ca5c804a10e6e0e6cb4450e820dba07b2e6bc575dfa.png"/></a>
            </div>
            <div class='footer-links' style='padding:0;font-size:11px;text-align:center'>
                <a href="/terms">Terms</a>
                /
                <a href="/privacy">Privacy</a>
            </div>
            <div class='copyright'>
                Copyright © 2017 StackShare, Inc. All rights reserved.
            </div>
        </div>
    </div>
</footer>

<div aria-hidden='true' aria-labelledby='myModalLabel' class='modal fade' id='signinModal' role='dialog'
     style='outline: none;z-index:10000' tabindex='-1'>
    <div class='modal-dialog'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button class='close' data-dismiss='modal' type='button'>
                    <span aria-hidden='true'>&times;</span>
                </button>
                <h4 id='myModalLabel'></h4>
            </div>
            <div class='modal-body' style='padding-top:0'>
                <div style='display: inline-flex;display: -webkit-inline-flex;'>
                    <div class='modal-welcome'>Welcome to</div>
                    <div class='brand-logo'></div>
                </div>
                <div class='social-login-blurb' style='margin:20px 0;'>
                    StackShare is a
                    <b>developer-only</b>
                    community of engineers, CTOs, VPEs, and
                    <br>developers from some of the world's top startups and companies.</br>
                </div>
                <div class='oauth-button' id='login-github'>
                    <a class='btn div-center' href='/users/auth/github'>
                        <div class='continue-with'>
                            Continue with Github
                        </div>
                        <span class='fa fa-github'></span>
                    </a>
                </div>
                <br>
                <div class='oauth-button' id='login-bitbucket'>
                    <a class='btn div-center' href='/users/auth/bitbucket'>
                        <div class='continue-with'>
                            Continue with Bitbucket
                        </div>
                        <span class='fa fa-bitbucket'></span>
                    </a>
                </div>
                <br>
                <div class='oauth-button' id='login-gitlab'>
                    <a class='btn div-center' href='/users/auth/gitlab'>
                        <div class='continue-with'>
                            Continue with Gitlab
                        </div>
                        <span class='fa fa-gitlab'></span>
                    </a>
                </div>
                <br>
                <div class='github-login-blurb disclaimer' style='margin:20px 0 45px 0'>
                    By clicking the sign up button above, you agree to our
                    <a target="_blank" href="/terms">Terms of Use</a>
                    and
                    <a target="_blank" href="/privacy">Privacy Policy</a>
                </div>

            </div>
        </div>
    </div>
</div>

<script>
    window._SENTRY_DSN = "https://2236262f9ba04066a5e149920fd6a3b7@app.getsentry.com/91238"
    window._MATCH_COMPANIES_INDEX = "Match_companies_production"
    window._MATCH_JOBS_INDEX = "Match_jobs_production"
    window._ALGOLIA_ID = "KM8652F2EG"
    window._ALGOLIA_API_KEY = "3c2f8c9f96b4218b3c5ca54731efdec0"
    window._MATCH_WORKER_JS_PATH = "/match_job_worker.js"
    window._SERVICES_INDEX = "Services_production"
    window.JS_SEGMENT_KEY = ""
    window.data = {current_user: {}}
</script>
<script>
    window._PAGE = {
        'name': "services.show",
        'properties': JSON.parse("{\"label\":\"Java\",\"contentGroupPage\":\"Services\",\"contentGroupAuthenticationStatus\":\"logged_in: false\"}")
    }
</script>

<script src="/assets/application-9034457b8f536df77a2acc2944065838344a18dbd55d334afadd2e2f1986352a.js"></script>
<script>
    window._SPONSORED_LINK_BLACKLIST = ["https://rollbar.com/", "https://sentry.io/welcome/?utm_source=stackshare&utm_medium=cpc&utm_campaign=stackshare-september", "https://www.datadoghq.com/lpg/?utm_source=Advertisement&utm_medium=StackShare&utm_campaign=StackShare-Other", "https://segment.com/?utm_medium=paid-display&utm_source=stackshare&utm_campaign=analytics"]
</script>

<script src="/webpack/application-8cf340abed7b61df4d0c.js"></script>
<script src="https://events.stackshare.io/setup.js"></script>
<script>
    //<![CDATA[
    UPLOADCARE_PUBLIC_KEY = "ef6555c1024d5e49c403";
    UPLOADCARE_LIVE = false;

    //]]>
</script>
<script>
    var trackOutboundLink = function (alink, cat, sitename) {
        var newpage = alink.target == "_blank";
        ga('send', 'event', cat, 'click', alink.href, {
            'hitCallback': function () {
                if (!newpage) {
                    document.location = alink.href;
                }
            }
        });

        return newpage;
    }
</script>
<script>
    $(document).ready(function () {
        UserVoice = window.UserVoice || [];
        (function () {
            var uv = document.createElement('script');
            uv.type = 'text/javascript';
            uv.async = true;
            uv.src = '//widget.uservoice.com/S7sNG8fZBUbqOKwFKHdXQ.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(uv, s)
        })();

        UserVoice.push(['set', {
            accent_color: '#448dd6',
            trigger_color: 'white',
            trigger_background_color: '#D2D2D2'
        }]);

        UserVoice.push(['addTrigger', {mode: 'contact', trigger_position: 'bottom-right'}]);

        UserVoice.push(['autoprompt', {}]);
    });
</script>
<script src='https://cdn.jsdelivr.net/selectize/0.12.3/js/standalone/selectize.min.js' type='text/javascript'></script>
</body>
</html>
