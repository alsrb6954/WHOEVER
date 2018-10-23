var keystone = require('keystone');
var request = require('request');

exports = module.exports = function (req, res) {

    var category = [
        {
            category: "과외",
            detail: [
                {
                    contents: "회화",
                    kind: ["영어", "중국어", "일어"]
                },
                {
                    contents: "프로그래밍",
                    kind: ["Android", "IOS", "Web"]
                },
                {
                    contents: "수능대비",
                    kind: []
                },
                {
                    contents: "자격증",
                    kind: []
                }
            ]
        },
        {
            category: "요리",
            detail: [
                {
                    contents: "디저트",
                    kind: ["커피", "제빵"]
                },
                {
                    contents: "한식",
                    kind: ["탕", "볶음"]
                },
                {
                    contents: "중식",
                    kind: ["탕", "볶음"]
                },
                {
                    contents: "일식",
                    kind: ["탕", "볶음"]
                },
                {
                    contents: "양식",
                    kind: ["탕", "볶음"]
                }
            ]
        },
        {
            category: "운동",
            detail: [
                {
                    contents: "스포츠",
                    kind: ["축구", "농구", "야구", "배구", "탁구", "배드민턴", "헬스", "테니스"]
                },
                {
                    contents: "하계스포츠",
                    kind: ["서핑", "스쿠버 다이빙", "수상스키"]
                },
                {
                    contents: "동계스포츠",
                    kind: ["보드", "스키", "스케이트"]
                },
            ]
        },
        {
            category: "미술",
            detail: [
                {
                    contents: "그림",
                    kind: ["동양화", "서양화"]
                },
                {
                    contents: "디자인",
                    kind: ["패션디자인", "시각디자인"]
                }
            ]
        },
        {
            category: "음악",
            detail: [
                {
                    contents: "보컬",
                    kind: ["성악", "실용음악"]
                },
                {
                    contents: "악기",
                    kind: ["바이올린", "피아노", "기타", "드럼"]
                }
            ]
        }
    ]
    
    console.log(category);
    res.json({data: category});
};
