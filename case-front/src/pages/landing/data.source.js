import React from 'react'
import logoImg from './img/wechat_pay_logo.png'
import getQueryString from '@/utils/getCookies'

const getCookies = getQueryString.getCookie
export const Banner30DataSource = {
  wrapper: { className: 'banner3' },
  textWrapper: {
    className: 'banner3-text-wrapper',
    children: [
      {
        name: 'slogan',
        className: 'banner3-slogan',
        children: (
          <div>
            <img src={logoImg} className="banner3-logo" alt="wechat-pay-logo" />
            <br />
            <span>JFTestCaseManager</span>
          </div>
        ),
      },
      {
        name: 'name',
        className: 'banner3-name',
        children: (
          <span>
            <p>项目测试用例管理/远程执行平台</p>
          </span>
        ),
      },
      {
        name: 'nameEn',
        className: 'banner3-name-en',
        children: (
          <span style={{ color: '#7d899b' }}>
            以脑图方式编辑可快速上手，用例关联需求形成流程闭环，并支持组件化引用，
            <br />
            可在各个平台嵌入使用，是测试人员的贴心助手
          </span>
        ),
      },
      {
        name: 'button',
        className: 'banner3-button',
        children: (
          <span>
            <p>开始使用</p>
          </span>
        ),
        href: getCookies('username') ? '/case/caseList/1' : `/login?/case/caseList/1`,
      },
    ],
  },
}
export const Footer00DataSource = {
  wrapper: { className: 'home-page-wrapper footer0-wrapper' },
  OverPack: { className: 'home-page footer0', playScale: 0.05 },
  copyright: {
    className: 'copyright',
    children: (
      <span>
        <p>
          <span>©2022 JFTestCaseManager</span>
        </p>
      </span>
    ),
  },
}
