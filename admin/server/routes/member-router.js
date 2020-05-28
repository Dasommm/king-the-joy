const express = require('express')

var oracledb = require('oracledb');
var config = require('../db/dbconfig.js');

const router = express.Router()

router.post('/selectList', function (request, response) {
    oracledb.getConnection(config, (err, conn) => {
        if (err) {
            console.error(err.message);
        }
        conn.execute("select * from member order by member_seq asc", {}, { outFormat: oracledb.OBJECT }, function (err, result) {
            if (err) {
                console.error(err.message);
                doRelease(conn);
            }
            console.log(result.rows);
            response.send(result.rows);
            doRelease(conn);
        })
    })
})

router.post('/selectOne', function (request, response) {
    oracledb.getConnection(config, (err, conn) => {
        if (err) {
            console.error(err.message);
        }
        var query = "select * from member where member_seq = :memberSeq";
        var bindData = [Number(request.body.memberSeq)];
        conn.execute(query, bindData, { outFormat: oracledb.OBJECT }, function (err, result) {
            if (err) {
                console.error(err.message);
                doRelease(conn);
            }
            console.log(result.rows);
            response.send(result.rows);
            doRelease(conn);
        })
    })
})

router.post('/updateDelflagY', function (request, response) {
    oracledb.getConnection(config, (err, conn) => {
        if (err) {
            console.error(err.message);
        }
        var query = "UPDATE MEMBER SET member_delflag = Y WHERE member_seq = :memberSeq";
        var bindData = [Number(request.body.memberSeq)];
        conn.execute(query, bindData, { outFormat: oracledb.OBJECT }, function (err, result) {
            if (err) {
                console.error(err.message);
                doRelease(conn);
            }
            doRelease(conn);
        })
    })
})

router.post('/updateDelflagN', function (request, response) {
    oracledb.getConnection(config, (err, conn) => {
        if (err) {
            console.error(err.message);
        }
        var query = "UPDATE MEMBER SET member_delflag = N WHERE member_seq = :memberSeq";
        var bindData = [Number(request.body.memberSeq)];
        conn.execute(query, bindData, { outFormat: oracledb.OBJECT }, function (err, result) {
            if (err) {
                console.error(err.message);
                doRelease(conn);
            }
            doRelease(conn);
        })
    })
})


function doRelease(connection) {
    connection.release(function (err) {
        if (err) {
            console.error(err.message);
        }
    });
}


module.exports = router