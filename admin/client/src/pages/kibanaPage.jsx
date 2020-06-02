import React, { Component } from 'react'
import Iframe from 'react-iframe'

class kibanaPage extends Component {
    render() {
        return (
            <Iframe url="http://localhost:5601/app/kibana#/visualize/edit/3d440630-a354-11ea-ae68-6f832bb07c22?_g=()&_a=(filters:!(),linked:!f,query:(language:lucene,query:''),uiState:(vis:(legendOpen:!t)),vis:(aggs:!((enabled:!t,id:'1',params:(),schema:metric,type:count),(enabled:!t,id:'2',params:(customInterval:'2h',extended_bounds:(),field:inptDt,interval:auto,min_doc_count:1),schema:segment,type:date_histogram),(enabled:!t,id:'3',params:(field:inptUid.keyword,missingBucket:!f,missingBucketLabel:Missing,order:desc,orderBy:'1',otherBucket:!f,otherBucketLabel:Other,size:5),schema:group,type:terms)),params:(addLegend:!t,addTimeMarker:!f,addTooltip:!t,categoryAxes:!((id:CategoryAxis-1,labels:(filter:!f,rotate:0,show:!t,truncate:200),position:left,scale:(type:linear),show:!t,style:(),title:(),type:category)),grid:(categoryLines:!f,style:(color:%23eee)),legendPosition:right,seriesParams:!((data:(id:'1',label:Count),drawLinesBetweenPoints:!t,mode:normal,show:!t,showCircles:!t,type:histogram,valueAxis:ValueAxis-1)),times:!(),type:histogram,valueAxes:!((id:ValueAxis-1,labels:(filter:!t,rotate:75,show:!t,truncate:100),name:LeftAxis-1,position:bottom,scale:(mode:normal,type:linear),show:!t,style:(),title:(text:Count),type:value))),title:tjdgns,type:horizontal_bar))"
                width="100%"
                height="700px"
                id="myId"
                className="myClassname"
                position="relative" />
        )
    }
}

export default kibanaPage