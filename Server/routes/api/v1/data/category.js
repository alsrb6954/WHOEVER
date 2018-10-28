var keystone = require('keystone');
var request = require('request');

exports = module.exports = function (req, res) {

    var category = [
        {
            category: "과외",
            contents: [
                {
                    detail: "회화",
                    detailContents: ["영어", "중국어", "일어"]
                },
                {
                    detail: "프로그래밍",
                    detailContents: ["Android", "IOS", "Web"]
                },
                {
                    detail: "수능대비",
                    detailContents: ["언어", "수리", "외국어", "과학탐구", "사회탐구", "제2외국어"]
                },
                {
                    detail: "자격증",
                    detailContents: ["기능사", "산업기사", "기사"]
                }
            ]
        },
        {
            category: "요리",
            contents: [
                {
                    detail: "디저트",
                    detailContents: ["커피", "제빵"]
                },
                {
                    detail: "한식",
                    detailContents: ["탕", "볶음"]
                },
                {
                    detail: "중식",
                    detailContents: ["탕", "볶음"]
                },
                {
                    detail: "일식",
                    detailContents: ["탕", "볶음"]
                },
                {
                    detail: "양식",
                    detailContents: ["탕", "볶음"]
                }
            ]
        },
        {
            category: "운동",
            contents: [
                {
                    detail: "스포츠",
                    detailContents: ["축구", "농구", "야구", "배구", "탁구", "배드민턴", "헬스", "테니스"]
                },
                {
                    detail: "하계스포츠",
                    detailContents: ["서핑", "스쿠버 다이빙", "수상스키"]
                },
                {
                    detail: "동계스포츠",
                    detailContents: ["보드", "스키", "스케이트"]
                },
            ]
        },
        {
            category: "미술",
            contents: [
                {
                    detail: "그림",
                    detailContents: ["동양화", "서양화"]
                },
                {
                    detail: "디자인",
                    detailContents: ["패션디자인", "시각디자인"]
                }
            ]
        },
        {
            category: "음악",
            contents: [
                {
                    detail: "보컬",
                    detailContents: ["성악", "실용음악"]
                },
                {
                    detail: "악기",
                    detailContents: ["바이올린", "피아노", "기타", "드럼"]
                }
            ]
        }
    ]

    console.log(category);
    res.status(200).json({category: category});
};
