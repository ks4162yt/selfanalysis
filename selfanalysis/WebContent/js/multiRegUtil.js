/**
 * 重回帰分析を計算する関数
 * 解がない場合は、0を返す
 *
 * @method multiReg
 * @param {Json} inData 入力したデータ
 * @return {Array} prc 偏回帰係数(partial regression coefficient)
 */
function multiReg(inData) {

    var x = [],
        y = [];

    //read input data
    for (var i = 0, max = inData.size; i < max; i++) {
        var data = inData.data[i];
        x[i] = [1], y[i] = [];
        for (var key in data) {
            if (key !== "total") {
                x[i].push(data[key]);
            } else {
                //total
                y[i].push(data[key]);
            }
        }
        if (y[i].size === 0) {
            return 0;
        }
    }

    //exec calculation
    var mX = new Matrix(x),
        mY = new Matrix(y);
    
    //[X^T*X]
    var tmp = Matrix.Mul(mX.Transpose(), mX);

    if (tmp.Determinant() === 0) {
        return 0;
    }
    //tmp^(-1)
    tmp = tmp.Inverse();

    //ret1*X^T*y
    tmp = Matrix.Mul(Matrix.Mul(tmp, mX.Transpose()), mY);

    var prc = [];
    for (var i = 0, max = tmp.col; i < max; i++) {
        prc.push(tmp.val[i][0]);
    }

    return prc;
}